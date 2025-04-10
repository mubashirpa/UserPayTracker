package com.example.userpaytracker.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.usecase.AddVisitorUseCase
import com.example.userpaytracker.domain.usecase.ClearUsersUseCase
import com.example.userpaytracker.domain.usecase.GetRandomUsersUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val addVisitorUseCase: AddVisitorUseCase,
    private val clearUsersUseCase: ClearUsersUseCase,
) : ViewModel() {
    private val _usersResult: MutableLiveData<Result<List<User>>> = MutableLiveData(Result.Empty())
    val usersResult: LiveData<Result<List<User>>> = _usersResult

    init {
        getRandomUsers()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.AddVisitor -> addVisitor(event.name, event.email)

            HomeUiEvent.ClearUsers -> clearUsers()

            HomeUiEvent.Retry -> getRandomUsers()
        }
    }

    private fun getRandomUsers() {
        getRandomUsersUseCase()
            .onEach { result ->
                _usersResult.value = result
            }.launchIn(viewModelScope)
    }

    private fun addVisitor(
        name: String,
        email: String,
    ) {
        addVisitorUseCase(name, email).launchIn(viewModelScope)
    }

    private fun clearUsers() {
        _usersResult.value = Result.Loading()
        clearUsersUseCase().launchIn(viewModelScope)
    }
}
