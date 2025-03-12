package com.example.userpaytracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userpaytracker.data.local.dao.UserDao
import com.example.userpaytracker.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
