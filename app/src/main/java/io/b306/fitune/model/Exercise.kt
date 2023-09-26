package io.b306.fitune.model

import io.b306.fitune.R

data class Exercise(
    val name: String,
    val imageResId: Int // 해당 운동의 이미지 리소스 ID
)

object ExerciseList {
    val list: List<Exercise> = listOf(
        Exercise("사이클링", R.drawable.ic_cycling),
        Exercise("수영", R.drawable.ic_swimming),
        Exercise("걷기", R.drawable.ic_walking),
        Exercise("조깅", R.drawable.ic_jogging),
        Exercise("런닝머신", R.drawable.ic_treadmill_running),
        Exercise("댄스", R.drawable.ic_dancing),
        Exercise("에어로빅", R.drawable.ic_aerobics),
        Exercise("체조", R.drawable.ic_gymnastics),
        Exercise("필라테스", R.drawable.ic_pilates),
        Exercise("요가", R.drawable.ic_yoga),
        Exercise("골프", R.drawable.ic_golf),
        Exercise("볼링", R.drawable.ic_bowling),
        Exercise("탁구", R.drawable.ic_table_tennis),
        Exercise("등산", R.drawable.ic_hiking),
        Exercise("태보", R.drawable.ic_tai_bo),
        Exercise("암벽등반", R.drawable.ic_rock_climbing),
        Exercise("헬스", R.drawable.ic_weightlifting),
        Exercise("승마", R.drawable.ic_horseback_riding),
        Exercise("자전거", R.drawable.ic_cycling),
        Exercise("캐치볼", R.drawable.ic_dodgeball),
        Exercise("풋살", R.drawable.ic_football),

    )
}