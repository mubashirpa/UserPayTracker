package com.example.userpaytracker.presentation.components

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.userpaytracker.databinding.LayoutDialogPaymentSuccessBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PaymentSuccessDialog : DialogFragment() {
    private var _binding: LayoutDialogPaymentSuccessBinding? = null
    private val binding get() = _binding!!

    private var setOnDismissListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = LayoutDialogPaymentSuccessBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext()).setView(binding.root)

        binding.continueButton.setOnClickListener {
            dismiss()
        }

        return dialog.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        setOnDismissListener?.invoke()
    }

    fun setOnDismissListener(listener: () -> Unit): PaymentSuccessDialog {
        setOnDismissListener = listener
        return this
    }

    companion object {
        val TAG: String = PaymentSuccessDialog::class.java.simpleName
    }
}
