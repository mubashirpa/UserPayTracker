package com.example.userpaytracker.presentation.paymentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.usecase.GetUserUseCase
import com.example.userpaytracker.navigation.Screen
import kotlinx.coroutines.launch

class PaymentDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    val id = savedStateHandle.toRoute<Screen.PaymentDetails>().id

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            getUserUseCase(id).collect {
                _user.value = it
            }
        }
    }
}
