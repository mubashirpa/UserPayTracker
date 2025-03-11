package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RandomUsersDto(
    val info: InfoDto,
    val results: List<UserDto>,
)
