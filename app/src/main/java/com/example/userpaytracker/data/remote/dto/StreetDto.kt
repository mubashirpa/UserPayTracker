package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StreetDto(
    val name: String? = null,
    val number: Int? = null,
)
