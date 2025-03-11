package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val md5: String? = null,
    val password: String? = null,
    val salt: String? = null,
    val sha1: String? = null,
    val sha256: String? = null,
    val username: String? = null,
    val uuid: String? = null,
)
