package com.example.userpaytracker.presentation.paymentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.domain.model.PaymentMethod
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.usecase.GetUserUseCase
import com.example.userpaytracker.domain.usecase.UpdateUserUseCase
import com.example.userpaytracker.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PaymentDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {
    val id = savedStateHandle.toRoute<Screen.PaymentDetails>().id

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _updateResult: MutableLiveData<Result<Boolean>> = MutableLiveData(Result.Empty())
    val updateResult: LiveData<Result<Boolean>> = _updateResult

    init {
        getUser()
    }

    fun onEvent(event: PaymentDetailsUiEvent) {
        when (event) {
            is PaymentDetailsUiEvent.Confirm -> updateUser(event.paymentAmount, event.paymentMethod)
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            getUserUseCase(id).collect {
                _user.value = it
            }
        }
    }

    private fun updateUser(
        paymentAmount: Double,
        paymentMethod: PaymentMethod,
    ) {
        updateUserUseCase(id, paymentAmount, paymentMethod)
            .onEach { result ->
                _updateResult.value = result
            }.launchIn(viewModelScope)
    }
}
