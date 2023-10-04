package io.b306.fitune.model

data class SignUpUser (
    var email: String,
    var password: String,
    var nickName: String,
    var height: Int,
    var weight: Int,
    var year: Int,
    var restingBpm: Int,
    var activeBpm: Int,
    var bodyFatPer: Int,
    var preferExerciseSeq: Int,
    var cellName : String
)