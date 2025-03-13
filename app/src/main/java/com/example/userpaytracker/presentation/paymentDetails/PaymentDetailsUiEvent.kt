package com.example.userpaytracker.presentation.paymentDetails

import com.example.userpaytracker.domain.model.PaymentMethod

sealed class PaymentDetailsUiEvent {
    data class Confirm(
        val paymentAmount: Double,
        val paymentMethod: PaymentMethod,
    ) : PaymentDetailsUiEvent()
}
