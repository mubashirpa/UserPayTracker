package com.example.userpaytracker.domain.repository

import com.example.userpaytracker.core.Result
import com.example.userpaytracker.data.local.entity.UserEntity
import com.example.userpaytracker.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    suspend fun getRandomUsers(
        page: Int = 1,
        results: Int = 20,
    ): Flow<Result<List<User>>>

    suspend fun getRandomUsersToLocal(
        page: Int = 1,
        results: Int = 20,
    )

    suspend fun getRandomUsersFromLocal(): Flow<List<User>>

    suspend fun getUser(id: Int): Flow<User>

    suspend fun upsertUser(user: UserEntity)

    suspend fun clearUsers()
}
