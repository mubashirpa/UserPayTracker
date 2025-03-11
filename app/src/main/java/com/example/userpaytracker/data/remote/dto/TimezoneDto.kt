package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TimezoneDto(
    val description: String,
    val offset: String,
)
