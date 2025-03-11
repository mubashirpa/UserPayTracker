package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String,
)
