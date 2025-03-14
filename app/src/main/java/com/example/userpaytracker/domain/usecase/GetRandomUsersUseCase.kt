package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.R
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetRandomUsersUseCase(
    private val repository: RandomUserRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    operator fun invoke(
        page: Int = 1,
        results: Int = 20,
    ): Flow<Result<List<User>>> =
        flow {
            try {
                emit(Result.Loading())
                repository.getRandomUsersFromLocal().collect { users ->
                    if (users.isNotEmpty()) {
                        emit(Result.Success(users))
                    } else {
                        repository.getRandomUsersToLocal(page, results)
                    }
                }
            } catch (e: Exception) {
                val message =
                    e.message?.let {
                        UiText.DynamicString(it)
                    } ?: UiText.StringResource(R.string.error_unknown)
                emit(Result.Error(message))
            }
        }.flowOn(ioDispatcher)
}
