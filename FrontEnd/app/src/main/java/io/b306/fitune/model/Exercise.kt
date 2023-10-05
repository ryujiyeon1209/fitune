package io.b306.fitune.model

import io.b306.fitune.R

data class Exercise(
    val englishName : String,
    val name: String,
    val imageResId: Int, // 해당 운동의 이미지 리소스 ID
    val exerciseId: Int  // 해당 운동의 ID
)

object ExerciseList {
    val list: List<Exercise> = listOf(
        Exercise("Cycling", "사이클링", R.drawable.ic_cycling, 1),
        Exercise("Swimming", "수영", R.drawable.ic_swimming, 2),
        Exercise("Walking","걷기", R.drawable.ic_walking, 3),
        Exercise("Jogging","달리기", R.drawable.ic_jogging, 4),
        Exercise("Treadmill Running","런닝머신", R.drawable.ic_treadmill_running, 5),
        Exercise("Dancing","댄스", R.drawable.ic_dancing, 6),
        Exercise( "Aerobics","에어로빅", R.drawable.ic_aerobics, 7),
        Exercise("gymnastics","체조", R.drawable.ic_gymnastics, 8),
        Exercise("Pilates","필라테스", R.drawable.ic_pilates, 9),
        Exercise("Yoga", "요가", R.drawable.ic_yoga, 10),
        Exercise("Golf", "골프", R.drawable.ic_golf, 11),
        Exercise("Bowling", "볼링", R.drawable.ic_bowling, 12),
        Exercise("Table Tennis","탁구", R.drawable.ic_table_tennis, 13),
        Exercise("Hiking", "등산", R.drawable.ic_hiking, 14),
        Exercise("Tai Bo", "태권도", R.drawable.ic_tai_bo, 15),
        Exercise("Rock Climbing","암벽등반", R.drawable.ic_rock_climbing, 16),
        Exercise("Weightlifting","헬스", R.drawable.ic_weightlifting, 17),
        Exercise("Horseback Riding", "승마", R.drawable.ic_horseback_riding, 18),
        Exercise("Bicycle Riding", "자전거", R.drawable.ic_cycling, 19),
        Exercise("Dodgeball", "캐치볼", R.drawable.ic_dodgeball,20),
        Exercise("Football","풋살", R.drawable.ic_football, 21),

    )
}

fun getImageResourceByExerciseName(exerciseName: String): Int {
    val exercise = ExerciseList.list.find { it.englishName == exerciseName }
    return exercise?.imageResId ?: R.drawable.ic_walking // null이면 그냥 걷기 반환!
}

fun getKoreanNAmeByExerciseName(exerciseName: String): String {
    val exercise = ExerciseList.list.find { it.englishName == exerciseName }
    return exercise?.name ?: "걷기" // null이면 그냥 걷기 반환!
}

fun getExerciseIdByName(exerciseName: String): Int {
    val exercise = ExerciseList.list.find { it.englishName == exerciseName }
    return exercise?.exerciseId ?: 3 // 일치하는 이름이 없으면 3(걷기) 반환
}

fun getKoreanNameByExerciseId(exerciseId: Int): String {
    val exercise = ExerciseList.list.find { it.exerciseId == exerciseId }
    return exercise?.name ?: "걷기" // 일치하는 ID가 없으면 "걷기" 반환
}

fun getImageResourceByExerciseId(exerciseId: Int): Int {
    val exercise = ExerciseList.list.find { it.exerciseId == exerciseId }
    return exercise?.imageResId ?: R.drawable.ic_walking // 일치하는 ID가 없으면 걷기 이미지 반환
}