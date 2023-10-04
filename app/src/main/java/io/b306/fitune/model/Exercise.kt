package io.b306.fitune.model

import io.b306.fitune.R

data class Exercise(
    val name: String,
    val imageResId: Int, // 해당 운동의 이미지 리소스 ID
    val exerciseId: Int  // 해당 운동의 ID
)

object ExerciseList {
    val list: List<Exercise> = listOf(
        Exercise("사이클링", R.drawable.ic_cycling, 1),
        Exercise("수영", R.drawable.ic_swimming, 2),
        Exercise("걷기", R.drawable.ic_walking, 3),
        Exercise("런닝", R.drawable.ic_jogging, 4),
        Exercise("런닝머신", R.drawable.ic_treadmill_running, 5),
        Exercise("댄스", R.drawable.ic_dancing, 6),
        Exercise("에어로빅", R.drawable.ic_aerobics, 7),
        Exercise("체조", R.drawable.ic_gymnastics, 8),
        Exercise("필라테스", R.drawable.ic_pilates, 9),
        Exercise("요가", R.drawable.ic_yoga, 10),
        Exercise("골프", R.drawable.ic_golf, 11),
        Exercise("볼링", R.drawable.ic_bowling, 12),
        Exercise("탁구", R.drawable.ic_table_tennis, 13),
        Exercise("등산", R.drawable.ic_hiking, 14),
        Exercise("태보", R.drawable.ic_tai_bo, 15),
        Exercise("암벽등반", R.drawable.ic_rock_climbing, 16),
        Exercise("헬스", R.drawable.ic_weightlifting, 17),
        Exercise("승마", R.drawable.ic_horseback_riding, 18),
        Exercise("자전거", R.drawable.ic_cycling, 19),
        Exercise("캐치볼", R.drawable.ic_dodgeball,20),
        Exercise("풋살", R.drawable.ic_football, 21),

    )
}