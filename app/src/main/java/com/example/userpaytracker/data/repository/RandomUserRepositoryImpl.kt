package com.example.userpaytracker.data.repository

import androidx.room.withTransaction
import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.utils.networkBoundResource
import com.example.userpaytracker.data.local.database.UserDatabase
import com.example.userpaytracker.data.mapper.toUser
import com.example.userpaytracker.data.mapper.toUserEntity
import com.example.userpaytracker.data.remote.api.RandomUserService
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.domain.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RandomUserRepositoryImpl(
    private val api: RandomUserService,
    private val database: UserDatabase,
) : RandomUserRepository {
    private val dao = database.userDao()

    override suspend fun getRandomUsers(
        page: Int,
        results: Int,
    ): Flow<Result<List<User>>> =
        networkBoundResource(
            query = {
                dao.getAll().map { it.map { it.toUser() } }
            },
            fetch = {
                api.getRandomUsers(page, results)
            },
            saveFetchResult = { users ->
                database.withTransaction {
                    dao.clearAll()
                    dao.insertAll(users.toUserEntity())
                }
            },
            shouldFetch = { it.isEmpty() },
        )
}
