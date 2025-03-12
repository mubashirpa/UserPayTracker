package com.example.userpaytracker.domain.repository

import com.example.userpaytracker.core.Result
import com.example.userpaytracker.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    suspend fun getRandomUsers(
        page: Int = 1,
        results: Int = 20,
    ): Flow<Result<List<User>>>
}
