package com.example.userpaytracker.data.repository

import com.example.userpaytracker.data.remote.api.RandomUserService
import com.example.userpaytracker.data.remote.dto.RandomUsersDto
import com.example.userpaytracker.domain.repository.RandomUserRepository

class RandomUserRepositoryImpl(
    private val api: RandomUserService,
) : RandomUserRepository {
    override suspend fun getRandomUsers(
        page: Int,
        results: Int,
    ): RandomUsersDto = api.getRandomUsers(page, results)
}
