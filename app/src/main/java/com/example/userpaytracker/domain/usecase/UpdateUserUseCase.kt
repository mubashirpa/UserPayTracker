package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import com.example.userpaytracker.data.mapper.toUserEntity
import com.example.userpaytracker.domain.model.PaymentMethod
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UpdateUserUseCase(
    private val repository: RandomUserRepository,
) {
    operator fun invoke(
        id: Int,
        paymentAmount: Double,
        paymentMethod: PaymentMethod,
    ): Flow<Result<Boolean>> =
        flow {
            try {
                emit(Result.Loading())
                val user = repository.getUser(id).first()
                repository.upsertUser(
                    user
                        .copy(
                            paymentAmount = paymentAmount,
                            paymentCompleted = true,
                            paymentMethod = paymentMethod,
                        ).toUserEntity(),
                )
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
