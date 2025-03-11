package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String,
)
