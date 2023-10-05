package io.b306.fitune.api

import io.b306.fitune.model.RecommendExercise

data class RecommendResponse (
    val message: String,
    val data: RecommendExercise
)