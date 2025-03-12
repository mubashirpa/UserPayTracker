package com.example.userpaytracker.di

import androidx.room.Room
import com.example.userpaytracker.core.Constants
import com.example.userpaytracker.data.local.database.UserDatabase
import com.example.userpaytracker.data.remote.api.RandomUserService
import com.example.userpaytracker.data.repository.RandomUserRepositoryImpl
import com.example.userpaytracker.domain.repository.RandomUserRepository
import com.example.userpaytracker.domain.usecase.AddVisitorUseCase
import com.example.userpaytracker.domain.usecase.ClearUsersUseCase
import com.example.userpaytracker.domain.usecase.GetRandomUsersUseCase
import com.example.userpaytracker.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
        single {
            Room
                .databaseBuilder(androidContext(), UserDatabase::class.java, "user-database")
                .build()
        }
        single<RandomUserRepository> { RandomUserRepositoryImpl(get(), get()) }
        single { GetRandomUsersUseCase(get()) }
        singleOf(::AddVisitorUseCase)
        singleOf(::ClearUsersUseCase)
        viewModelOf(::HomeViewModel)
    }
