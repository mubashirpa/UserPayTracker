package com.example.userpaytracker.presentation.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.userpaytracker.databinding.LayoutDialogAddVisitorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddVisitorBottomSheet : BottomSheetDialogFragment() {
    private var _binding: LayoutDialogAddVisitorBinding? = null
    private val binding get() = _binding!!

    private var onAddVisitorClickListener: ((dialog: AddVisitorBottomSheet, name: String, email: String) -> Unit)? =
        null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutDialogAddVisitorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            val name =
                binding.nameField.editText
                    ?.text
                    .toString()
            val email =
                binding.emailField.editText
                    ?.text
                    .toString()
            onAddVisitorClickListener?.invoke(this, name, email)
        }

        binding.nameField.editText?.doOnTextChanged { text, _, _, _ ->
            validateFields()
        }

        binding.emailField.editText?.doOnTextChanged { text, _, _, _ ->
            validateFields()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setOnAddVisitorClickListener(
        listener: (dialog: AddVisitorBottomSheet, name: String, email: String) -> Unit,
    ): AddVisitorBottomSheet {
        onAddVisitorClickListener = listener
        return this
    }

    private fun validateFields() {
        val isNameNotEmpty =
            !binding.nameField.editText
                ?.text
                .isNullOrBlank()
        val isEmailNotEmpty =
            !binding.emailField.editText
                ?.text
                .isNullOrBlank()

        binding.saveButton.isEnabled = isNameNotEmpty && isEmailNotEmpty
    }

    companion object {
        val TAG: String = AddVisitorBottomSheet::class.java.simpleName
    }
}
