package io.b306.fitune.model

data class MyInfo (
    val userSeq: Int,
    val email: String,
    val password: String,
    val nickname: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val bodyFatPercentage: Int,
    val activeBPM: Int,
    val tension: Int,
    val restingBPM: Int
)