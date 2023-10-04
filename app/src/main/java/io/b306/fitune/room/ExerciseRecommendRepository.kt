package io.b306.fitune.room

import kotlinx.coroutines.flow.Flow

class ExerciseRecommendRepository(private val exerciseRecommendDao: ExerciseRecommendDao) {
    fun fetchExerciseRecommendById(id: Int): Flow<ExerciseRecommendEntity> {
        return exerciseRecommendDao.fetchExerciseRecommendById(id)
    }

}