package io.b306.fitune.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseRecommendDao {
    // 시간이 걸릴 수 있기 때문에 주 스레드에서 하면 안 됨
    // Coroutines를 통해 사용할 수 있는 백그라운드 스레드에서 수행해야 함
    // 그래서 suspend fun
    @Insert
    suspend fun insert(exerciseRecommendEntity: ExerciseRecommendEntity)

    @Update
    suspend fun update(exerciseRecommendEntity: ExerciseRecommendEntity)

    @Delete
    suspend fun delete(exerciseRecommendEntity: ExerciseRecommendEntity)

    @Query("SELECT * FROM myinfo_table WHERE id = :id")
    fun fetchExerciseRecommendById(id: Int): Flow<ExerciseRecommendEntity>
}