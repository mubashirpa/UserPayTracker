package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NameDto(
    val first: String? = null,
    val last: String? = null,
    val title: String? = null,
)
