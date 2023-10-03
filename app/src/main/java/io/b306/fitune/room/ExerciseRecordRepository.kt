package io.b306.fitune.room

import kotlinx.coroutines.flow.Flow

class ExerciseRecordRepository(private val exerciseRecordDao: ExerciseRecordDao) {
    fun fetchAllExerciseRecord(): Flow<List<ExerciseRecordEntity>> {
        return exerciseRecordDao.fetchAllExerciseRecord()
    }
}