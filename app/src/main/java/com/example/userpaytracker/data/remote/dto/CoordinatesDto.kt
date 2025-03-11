package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDto(
    val latitude: String,
    val longitude: String,
)
