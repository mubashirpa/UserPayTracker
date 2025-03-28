package com.example.userpaytracker.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class PaymentDetails(
        val id: Int,
    ) : Screen()
}
