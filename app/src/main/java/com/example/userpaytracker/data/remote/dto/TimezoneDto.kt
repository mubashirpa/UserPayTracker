package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TimezoneDto(
    val description: String? = null,
    val offset: String? = null,
)
