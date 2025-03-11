package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PictureDto(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null,
)
