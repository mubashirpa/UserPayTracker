package com.example.userpaytracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val city: String? = null,
    val coordinates: CoordinatesDto? = null,
    val country: String? = null,
    val postcode: String? = null,
    val state: String? = null,
    val street: StreetDto? = null,
    val timezone: TimezoneDto? = null,
)
