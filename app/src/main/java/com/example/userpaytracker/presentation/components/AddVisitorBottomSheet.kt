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

    private var onAddVisitorClickListener: ((dialog: AddVisitorBottomSheet, name: String) -> Unit)? =
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
            onAddVisitorClickListener?.invoke(this, name)
        }

        binding.nameField.editText?.doOnTextChanged { text, _, _, _ ->
            binding.saveButton.isEnabled = text?.isNotBlank() == true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setOnAddVisitorClickListener(listener: (dialog: AddVisitorBottomSheet, name: String) -> Unit): AddVisitorBottomSheet {
        onAddVisitorClickListener = listener
        return this
    }

    companion object {
        val TAG: String = AddVisitorBottomSheet::class.java.simpleName
    }
}
