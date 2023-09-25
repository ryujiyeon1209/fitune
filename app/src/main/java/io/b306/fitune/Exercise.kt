package io.b306.fitune

data class Exercise(
    val name: String,
    val imageResId: Int // 해당 운동의 이미지 리소스 ID
)

object ExerciseList {
    val list: List<Exercise> = listOf(
        Exercise("사이클", R.drawable.ic_bicycle),
        Exercise("달리기", R.drawable.ic_run),
        Exercise("수영", R.drawable.ic_swim),
        // 나머지 운동 아이템들 추가
    )
}