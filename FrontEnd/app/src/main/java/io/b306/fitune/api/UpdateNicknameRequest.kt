package io.b306.fitune.api

data class UpdateNicknameRequest(
    val userSeq: Int,
    val nickname: String
)