package com.example.userpaytracker.di

import com.example.userpaytracker.core.Constants
import com.example.userpaytracker.data.remote.api.RandomUserService
import com.example.userpaytracker.data.repository.RandomUserRepositoryImpl
import com.example.userpaytracker.domain.repository.RandomUserRepository
import com.example.userpaytracker.domain.usecase.GetRandomUsersUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule =
    module {
        single {
            Retrofit
                .Builder()
                .baseUrl(Constants.RANDOM_USER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserService::class.java)
        }
        single<RandomUserRepository> { RandomUserRepositoryImpl(get()) }
        singleOf(::GetRandomUsersUseCase)
    }
