package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NameDto(
    val first: String,
    val last: String,
    val title: String,
)
