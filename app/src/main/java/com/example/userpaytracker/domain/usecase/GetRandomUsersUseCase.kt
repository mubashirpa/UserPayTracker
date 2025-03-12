package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import com.example.userpaytracker.data.mapper.toUser
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRandomUsersUseCase(
    private val repository: RandomUserRepository,
) {
    operator fun invoke(
        page: Int = 1,
        results: Int = 20,
    ): Flow<Result<List<User>>> =
        flow {
            try {
                emit(Result.Loading())
                val users = repository.getRandomUsers(page, results).toUser()
                emit(Result.Success(users))
            } catch (e: Exception) {
                e.message?.let { message ->
                    emit(Result.Error(UiText.DynamicString(message)))
                } ?: emit(Result.Error(UiText.StringResource(R.string.error_unknown)))
            }
        }
}
