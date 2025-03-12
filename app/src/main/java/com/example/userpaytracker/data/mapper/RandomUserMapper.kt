package com.example.userpaytracker.data.mapper

import com.example.userpaytracker.data.local.entity.UserEntity
import com.example.userpaytracker.data.remote.dto.RandomUsersDto
import com.example.userpaytracker.domain.model.User

fun RandomUsersDto.toUserEntity(): List<UserEntity> =
    results
        ?.map {
            UserEntity(
                name = "${it.name?.first} ${it.name?.last}",
                paymentAmount = 2500.0,
                paymentCompleted = false,
                paymentMethod = null,
                picture = it.picture?.medium,
            )
        }.orEmpty()

fun UserEntity.toUser(): User =
    User(
        id = id,
        name = name,
        paymentAmount = paymentAmount,
        paymentCompleted = paymentCompleted,
        paymentMethod = paymentMethod,
        picture = picture,
    )
