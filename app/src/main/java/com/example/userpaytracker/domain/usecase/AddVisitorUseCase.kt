package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import com.example.userpaytracker.data.local.entity.UserEntity
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddVisitorUseCase(
    private val repository: RandomUserRepository,
) {
    operator fun invoke(
        name: String,
        email: String,
    ): Flow<Result<Boolean>> =
        flow {
            try {
                emit(Result.Loading())
                val user =
                    UserEntity(
                        email = email,
                        name = name,
                        paymentAmount = 1000.0,
                        paymentCompleted = false,
                        paymentMethod = null,
                        picture = null,
                    )
                repository.upsertUser(user)
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
