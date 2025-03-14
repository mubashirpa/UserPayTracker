package com.example.userpaytracker.presentation.paymentDetails

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.databinding.FragmentPaymentDetailsBinding
import com.example.userpaytracker.domain.model.PaymentMethod
import com.example.userpaytracker.presentation.components.PaymentSuccessDialog
import com.example.userpaytracker.presentation.core.utils.dpToPx
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit
import kotlin.getValue

class PaymentDetailsFragment : Fragment() {
    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentDetailsViewModel by viewModel()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPaymentDetailsBinding.inflate(inflater, container, false)
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(200, TimeUnit.MILLISECONDS)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updateLayoutParams<MarginLayoutParams> {
                leftMargin = bars.left
                topMargin = bars.top
                bottomMargin = bars.bottom
                rightMargin = bars.right
            }
            WindowInsetsCompat.CONSUMED
        }

        binding.profile.transitionName = requireArguments().getInt("id").toString()

        viewModel.user.observe(viewLifecycleOwner) {
            binding.name.text = it.name
            binding.email.text = it.email
            binding.amount.setText(it.paymentAmount?.toInt().toString())

            binding.profile.load(it.picture) {
                crossfade(true)
                placeholder(R.drawable.bg_placeholder)
            }

            if (it.paymentCompleted == true) {
                binding.profile.strokeWidth = dpToPx(3f, requireContext()).toFloat()
            }

            when (it.paymentMethod) {
                PaymentMethod.UPI -> binding.upi.isChecked = true
                PaymentMethod.CASH -> binding.cash.isChecked = true
                else -> {}
            }
        }

        viewModel.updateResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> {
                    showMessage(getString(R.string.error_unknown))
                }

                is Result.Success -> {
                    val dialog =
                        PaymentSuccessDialog()
                            .setOnDismissListener {
                                navController.navigateUp()
                            }
                    dialog.isCancelable = false
                    dialog.show(childFragmentManager, PaymentSuccessDialog.TAG)
                }

                else -> {}
            }
        }

        binding.upi.setOnClickListener {
            binding.cash.isChecked = false
        }

        binding.cash.setOnClickListener {
            binding.upi.isChecked = false
        }

        binding.payButton.setOnClickListener {
            val amount =
                binding.amount.text
                    .toString()
                    .toDoubleOrNull() ?: 0.0
            if (amount < 1) {
                showMessage(getString(R.string.amount_cannot_be_less_than_1))
                return@setOnClickListener
            }

            val paymentMethod =
                when {
                    binding.upi.isChecked -> PaymentMethod.UPI
                    binding.cash.isChecked -> PaymentMethod.CASH
                    else -> {
                        showMessage(getString(R.string.please_select_a_payment_method))
                        return@setOnClickListener
                    }
                }

            viewModel.onEvent(PaymentDetailsUiEvent.Confirm(amount, paymentMethod))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMessage(message: String) {
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.radioGroup)
            .show()
    }
}
