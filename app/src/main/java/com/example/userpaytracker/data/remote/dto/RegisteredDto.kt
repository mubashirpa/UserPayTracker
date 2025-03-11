package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisteredDto(
    val age: Int,
    val date: String,
)
