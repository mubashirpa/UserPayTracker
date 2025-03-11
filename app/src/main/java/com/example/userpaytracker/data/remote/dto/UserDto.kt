package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val cell: String? = null,
    val dob: DobDto? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: IdDto? = null,
    val location: LocationDto? = null,
    val login: LoginDto? = null,
    val name: NameDto? = null,
    val nat: String? = null,
    val phone: String? = null,
    val picture: PictureDto? = null,
    val registered: RegisteredDto? = null,
)
