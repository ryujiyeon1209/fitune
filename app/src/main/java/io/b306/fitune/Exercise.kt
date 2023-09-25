package io.b306.fitune

data class Exercise(
    val name: String,
    val imageResId: Int // 해당 운동의 이미지 리소스 ID
)

object ExerciseList {
    val list: List<Exercise> = listOf(
        Exercise("사이클링", R.drawable.ic_Cycling),
        Exercise("수영", R.drawable.ic_Swimming),
        Exercise("걷기", R.drawable.ic_Walking),
        Exercise("조깅", R.drawable.ic_Jogging),
        Exercise("런닝머신", R.drawable.ic_Tredmill Running),
        Exercise("댄스", R.drawable.ic_Dancing),
        Exercise("에어로빅", R.drawable.ic_Aerobices),
        Exercise("체조", R.drawable.ic_Gymnastics),
        Exercise("필라테스", R.drawable.ic_Pilates),
        Exercise("요가", R.drawable.ic_Yoga),
        Exercise("골프", R.drawable.ic_Golf),
        Exercise("볼링", R.drawable.ic_Bowling),
        Exercise("탁구", R.drawable.ic_TableTennis),
        Exercise("등산", R.drawable.ic_Hiking),
        Exercise("태보", R.drawable.ic_TaiBo),
        Exercise("암벽등반", R.drawable.ic_RockClimbing),
        Exercise("헬스", R.drawable.ic_Weightlifting),
        Exercise("승마", R.drawable.ic_HorsebackRiding),
        Exercise("자전거", R.drawable.ic_Cycling),
        Exercise("캐치볼", R.drawable.ic_Dodgeball),
        Exercise("풋살", R.drawable.ic_Football),

    )
}