package com.example.userpaytracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.userpaytracker.domain.model.PaymentMethod

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String?,
    val name: String?,
    val paymentAmount: Double?,
    val paymentCompleted: Boolean?,
    val paymentMethod: PaymentMethod?,
    val picture: String?,
)
