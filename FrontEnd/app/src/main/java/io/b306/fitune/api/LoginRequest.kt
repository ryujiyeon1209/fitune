package io.b306.fitune.api

import io.b306.fitune.model.User

data class LoginRequest(
    val email: String,
    val password: String
)