package io.b306.fitune.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.b306.fitune.room.converter.Converters

@Database(entities = [MyInfoEntity::class, ExerciseRecordEntity::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FituneDatabase: RoomDatabase() {
    abstract fun myInfoDao(): MyInfoDao
    abstract fun exerciseRecordDao(): ExerciseRecordDao // 이 부분은 ExerciseRecordDao 인터페이스를 정의한 후에 사용하세요.

    companion object {
        @Volatile
        private var INSTANCE: FituneDatabase? = null

        fun getInstance(context: Context): FituneDatabase {
            // 동기화
            // 여러 스레드가 데이터베이스를 요청할 때 한 번만 초기화 하도록
            // synchronized 함수를 사용 - 한 번에 하나의 스레드만 블록에 들어올 수 있음
            synchronized(this) {
                // INSTANCE의 현재 값을 로컬 변수에 복사
                // 코틀린은 스마트캐스트 가능하고 로컬 변수에만 사용 가능
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        // context.applicationContext를 사용해
                        // 데이터베이스를 구축
                        context.applicationContext,
                        FituneDatabase::class.java,
                        "fitune_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    // 이제 INSTANCE 객체에 instance를 할당 가능
                    INSTANCE = instance
                    // 이제 INSTANCE는 인스턴스 전역 객체에 할당
                }
                // getInstance는 instance를 반환
                return instance
            }
        }
    }
}