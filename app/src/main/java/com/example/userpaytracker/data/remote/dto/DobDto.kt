package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DobDto(
    val age: Int? = null,
    val date: String? = null,
)
