package io.b306.fitune.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MyInfoDao {

    // 시간이 걸릴 수 있기 때문에 주 스레드에서 하면 안 됨
    // Coroutines를 통해 사용할 수 있는 백그라운드 스레드에서 수행해야 함
    // 그래서 suspend fun
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myInfoEntity: MyInfoEntity)

    @Update
    suspend fun update(myInfoEntity: MyInfoEntity)

    @Delete
    suspend fun delete(myInfoEntity: MyInfoEntity)

    @Query("DELETE FROM myinfo_table")
    suspend fun deleteAll()

    // 검색 은 두 가지 방법이 있음
    // 1. 전체 데이터 가져와서 특정 id가 있는 데이터 검색
    // 테이블 이름은 작은따옴표로
//    @Query("Select * from `myinfo_table`")
//    fun fetchAllEmployee(): Flow<List<MyInfoEntity>>
    // 이거 안 씀

    // 2. 특성 항목 검색(id값으로 검색)
    // 값이 1개로 고정이므로 하드 코딩 가능 - 파라미터 없어도 됨
    // 있어야 할 때는 아래처럼 쓰면 됨
    // @Query("Select * from `myinfo_table` where id=:id")
    // fun fetchEmployeeById(id: Int): Flow<MyInfoEntity>
    @Query("Select * from `myinfo_table` where id=1")
    suspend fun getMyInfo(): MyInfoEntity?
}