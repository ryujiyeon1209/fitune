package io.b306.fitune.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.b306.fitune.model.RecommendExercise

@Entity
data class ExerciseRecommendEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1, // id 필드를 추가

    var recommendExercise1: String = "",
    var recommendExercise2: String = "",
    var recommendExercise3: String = "",
    var targetTime: Int = 0,
    var targetBpm: Int = 0,
)