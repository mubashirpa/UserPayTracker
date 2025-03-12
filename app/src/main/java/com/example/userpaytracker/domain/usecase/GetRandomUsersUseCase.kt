package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.core.Result
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetRandomUsersUseCase(
    private val repository: RandomUserRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke(
        page: Int = 1,
        results: Int = 20,
    ): Flow<Result<List<User>>> =
        withContext(ioDispatcher) {
            repository.getRandomUsers(page, results)
        }
}
