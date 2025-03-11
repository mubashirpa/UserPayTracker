package com.example.userpaytracker.data.remote.api

import com.example.userpaytracker.data.remote.dto.RandomUsersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 20,
    ): RandomUsersDto
}
