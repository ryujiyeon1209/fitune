package io.b306.fitune.api

data class ExerciseRequestBody(
    val exerciseListSeq: Int,
    val recommended: Boolean = true,
    val exerciseStart: String,
    val exerciseEnd: String,
    val exerciseAvgBpm: Int,
    val exerciseMaxBpm: Int,
    val review: Int,
    val weather: Int
)