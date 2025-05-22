package com.libroom.core.data.api

import com.libroom.core.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    val email: String,
    val token: String
)

fun UserDto.toUser() = User(
    name = name,
    email = email
)
