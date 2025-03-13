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
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.userpaytracker.R
import com.example.userpaytracker.databinding.FragmentPaymentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit
import kotlin.getValue

class PaymentDetailsFragment : Fragment() {
    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentDetailsViewModel by viewModel()

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
                error(R.drawable.baseline_person_24)
            }
        }

        binding.upi.setOnClickListener {
            binding.cash.isChecked = false
        }

        binding.cash.setOnClickListener {
            binding.upi.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
