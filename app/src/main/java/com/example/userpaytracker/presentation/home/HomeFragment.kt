package com.example.userpaytracker.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.databinding.FragmentHomeBinding
import com.example.userpaytracker.presentation.components.AddVisitorBottomSheet
import com.example.userpaytracker.presentation.core.utils.dpToPx
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.fragmentContainer) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<MarginLayoutParams> {
                leftMargin = insets.left
                bottomMargin = insets.bottom
                rightMargin = insets.right
            }
            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.recyclerView) { v, insets ->
            val bars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updatePadding(
                left = bars.left,
                right = bars.right,
                bottom = bars.bottom + dpToPx(88f, requireContext()),
            )
            WindowInsetsCompat.CONSUMED
        }

        val adapter =
            HomeAdapter(
                context = requireContext(),
                navigateToPaymentDetails = { /*TODO*/ },
            )
        binding.recyclerView.adapter = adapter

        viewModel.usersResult.observe(viewLifecycleOwner) { result ->
            val users = result.data.orEmpty()
            val message = result.message?.asString(requireContext())

            when (result) {
                is Result.Empty -> {}

                is Result.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    if (users.isEmpty()) {
                        binding.errorText.text = message
                        binding.errorView.visibility = View.VISIBLE
                    } else {
                        Snackbar
                            .make(
                                requireContext(),
                                binding.root,
                                message ?: "",
                                Snackbar.LENGTH_INDEFINITE,
                            ).setAction(R.string.retry) {
                                viewModel.onEvent(HomeUiEvent.Retry)
                            }.setAnchorView(binding.extendedFab)
                            .show()
                    }
                }

                is Result.Loading -> {
                    binding.errorView.visibility = View.GONE
                    binding.progressCircular.visibility = View.VISIBLE
                    if (users.isNotEmpty()) {
                        adapter.submitList(users) {
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                    }
                }

                is Result.Success -> {
                    adapter.submitList(users) {
                        binding.progressCircular.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.onEvent(HomeUiEvent.Retry)
        }

        binding.extendedFab.setOnClickListener {
            AddVisitorBottomSheet()
                .setOnAddVisitorClickListener { dialog, name ->
                    dialog.dismiss()
                    viewModel.onEvent(HomeUiEvent.AddVisitor(name))
                }.show(childFragmentManager, AddVisitorBottomSheet.TAG)
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.clear -> {
                    viewModel.onEvent(HomeUiEvent.ClearUsers)
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
