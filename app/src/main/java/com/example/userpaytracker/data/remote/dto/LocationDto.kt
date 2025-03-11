package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val city: String,
    val coordinates: CoordinatesDto,
    val country: String,
    val postcode: Int,
    val state: String,
    val street: StreetDto,
    val timezone: TimezoneDto,
)
