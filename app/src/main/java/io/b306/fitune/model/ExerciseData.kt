package io.b306.fitune.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExerciseData(
    var exerciseSeq: Int = 0,
    var startTimeMillis: String = "",
    var endTimeMillis: String = "",
    var avgHeartRate: Int = 0,
    var maxHeartRate: Int = 0,
    var exerciseReview: Int = 0,
    var exerciseWeather: Int = 0
) : Parcelable