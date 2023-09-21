package io.b306.fitune

data class CalendarDayModel(
    val day: Int,
    val isCurrentMonth: Boolean,
    val date: String,
    val exerciseName: String? = null,
    val exerciseStart: String? = null,  // datetime을 String으로 받아오는 것으로 가정
    val exerciseEnd: String? = null,    // datetime을 String으로 받아오는 것으로 가정
    val avgBpm: Int? = null,
    val maxBpm: Int? = null,
    val review: Int? = null
)
