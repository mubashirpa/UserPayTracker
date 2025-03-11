package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class IdDto(
    val name: String? = null,
    val value: String? = null,
)
