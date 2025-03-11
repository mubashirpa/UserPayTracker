package com.example.userpaytracker.domain.repository

import com.example.userpaytracker.data.remote.dto.RandomUsersDto

interface RandomUserRepository {
    suspend fun getRandomUsers(
        page: Int = 1,
        results: Int = 20,
    ): RandomUsersDto
}
