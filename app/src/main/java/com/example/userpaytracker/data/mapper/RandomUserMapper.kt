package com.example.userpaytracker.data.mapper

import com.example.userpaytracker.data.remote.dto.RandomUsersDto
import com.example.userpaytracker.domain.model.User

fun RandomUsersDto.toUser(): List<User> =
    results
        ?.map {
            User(
                name = "${it.name?.first} ${it.name?.last}",
                picture = it.picture?.medium,
            )
        }.orEmpty()
