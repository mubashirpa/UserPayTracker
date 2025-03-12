package com.example.userpaytracker.presentation.home

sealed class HomeUiEvent {
    data class AddVisitor(
        val name: String,
        val email: String,
    ) : HomeUiEvent()

    object ClearUsers : HomeUiEvent()

    object Retry : HomeUiEvent()
}
