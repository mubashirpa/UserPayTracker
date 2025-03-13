package com.example.userpaytracker.domain.usecase

import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val repository: RandomUserRepository,
) {
    suspend operator fun invoke(id: Int): Flow<User> = repository.getUser(id)
}
