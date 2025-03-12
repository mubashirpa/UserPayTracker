package com.example.userpaytracker.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.userpaytracker.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertAll(users: List<UserEntity>)

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserEntity>>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
