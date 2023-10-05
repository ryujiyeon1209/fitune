package io.b306.fitune.api

import io.b306.fitune.model.User

data class UserResponse(
    val message: String,
    val data: User
)
