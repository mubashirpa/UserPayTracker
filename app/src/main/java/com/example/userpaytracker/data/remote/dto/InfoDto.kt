package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val page: Int? = null,
    val results: Int? = null,
    val seed: String? = null,
    val version: String? = null,
)
