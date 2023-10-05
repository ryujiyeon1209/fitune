package io.b306.fitune.model

import com.google.gson.annotations.SerializedName
import io.b306.fitune.api.PreferExerciseResponse

data class MyInfoData(
    @SerializedName("user") val user: MyInfo?,
    @SerializedName("cell") val cell: Cell?,
    @SerializedName("preferExerciseResponse") val preferExerciseResponse: PreferExerciseResponse?,
    @SerializedName("exerciseRecord") val exerciseRecord: List<ExerciseRecord>?
)