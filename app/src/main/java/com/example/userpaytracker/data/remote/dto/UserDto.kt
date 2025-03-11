package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val cell: String,
    val dob: DobDto,
    val email: String,
    val gender: String,
    val id: IdDto,
    val location: LocationDto,
    val login: LoginDto,
    val name: NameDto,
    val nat: String,
    val phone: String,
    val picture: PictureDto,
    val registered: RegisteredDto,
)
