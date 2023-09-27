package io.b306.fitune.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseRecordDao {
    // 시간이 걸릴 수 있기 때문에 주 스레드에서 하면 안 됨
    // Coroutines를 통해 사용할 수 있는 백그라운드 스레드에서 수행해야 함
    // 그래서 suspend fun
    @Insert
    suspend fun insert(exerciseRecordEntity: ExerciseRecordEntity)

    @Update
    suspend fun update(exerciseRecordEntity: ExerciseRecordEntity)

    @Delete
    suspend fun delete(exerciseRecordEntity: ExerciseRecordEntity)

    // 검색 은 두 가지 방법이 있음
    // 1. 전체 데이터 가져와서 특정 id가 있는 데이터 검색
    // 테이블 이름은 백틱
    @Query("Select * from `exercise_record_table`")
    fun fetchAllExerciseRecord(): Flow<List<ExerciseRecordEntity>>
    // 2. 특성 항목 검색(id값으로 검색)
    @Query("Select * from `exercise_record_table` where id=:id")
    fun fetchExerciseRecordById(id: Int): Flow<ExerciseRecordEntity>
}