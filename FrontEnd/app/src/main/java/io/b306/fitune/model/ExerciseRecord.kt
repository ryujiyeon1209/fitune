package io.b306.fitune.model

data class ExerciseRecord(
    val exerciseRecordSeq: Int,
    val exerciseStart: String,
    val exerciseEnd: String,
    val exerciseReco: Boolean,
    val exerciseAvgBpm: Int,
    val exerciseMaxBpm: Int,
    val exerciseDistance: Int,
    val exerciseReview: Int,
    val exerciseWeather: Int
)