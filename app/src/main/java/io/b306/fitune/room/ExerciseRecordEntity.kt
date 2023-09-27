package io.b306.fitune.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "exercise_record_table",
    foreignKeys = [ForeignKey(
        entity = MyInfoEntity::class, // MyInfoEntity가 부모 엔터티라는 것을 나타냄
        parentColumns = ["id"], // 부모 엔터티인 MyInfoEntity에서 참조하는 칼럼
        childColumns = ["userId"], // 자식 엔터티인 exercise_record_table에서 참조되는 칼럼
        onDelete = ForeignKey.CASCADE // 유저가 삭제되면 해당 운동 기록도 삭제
    )]
)
data class ExerciseRecordEntity(
    var exerciseRecordSeq: Int = 0, // 운동 기록 번호
    var exerciseAvgBpm: Int = 0, // 평균 심박수
    var exerciseDistance: Int = 0, // 움직인 거리
    var exerciseStart: Date? = null, // 운동 시작 시간
    var exerciseEnd: Date? = null, //  운동 종료 시간
    var exerciseMaxBpm: Int = 0,
    var exerciseReco: Boolean = false, // 이거 mySQL에서 BIT 타입
    var exerciseReview: Int = 0,
    var exerciseWeather: Int = 0,
    var exerciseListSeq: Int = 0, // 이거 뭐자


    @ColumnInfo(index = true)
    val userId: Int = 1 // MyInfoEntity의 id를 참조
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}