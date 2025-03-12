package com.example.userpaytracker.domain.model

data class User(
    val id: Int? = null,
    val name: String? = null,
    val paymentAmount: Double? = null,
    val paymentCompleted: Boolean? = null,
    val paymentMethod: PaymentMethod? = null,
    val picture: String? = null,
)
