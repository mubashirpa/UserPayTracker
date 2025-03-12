package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClearUsersUseCase(
    private val repository: RandomUserRepository,
) {
    operator fun invoke(): Flow<Result<Boolean>> =
        flow {
            try {
                emit(Result.Loading())
                repository.clearUsers()
                emit(Result.Success(true))
            } catch (e: Exception) {
                val message =
                    e.message?.let {
                        UiText.DynamicString(it)
                    } ?: UiText.StringResource(R.string.error_unknown)
                emit(Result.Error(message))
            }
        }
}
