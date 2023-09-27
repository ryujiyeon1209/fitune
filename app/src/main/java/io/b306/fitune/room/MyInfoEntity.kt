package io.b306.fitune.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// 데이터 모델 클래스 만들기
@Entity(tableName = "myinfo_table")
data class MyInfoEntity (
    // 기본 유저 정보
    var userSeq: Int = 0,
    var nickname: String = "",
    var email: String = "",
    var birth: String = "",
    var bpm: Int = 0,
    var restingBpm: Int = 0,
    var activeBpm: Int = 0,
    var height: Int = 0,
    var weight: Int = 0,
    // 유저의 세포
    var cellSeq: Int = 0,
    var cellExp: Int = 0,
    var cellName: String = "",
    // 선호 운동
    var preferExercise: String = "",
    // 운동 기록은 따로 Entity 만듦

) {
    @PrimaryKey
    var id: Int = 1 // 이 값이 항상 1이기 때문에, MyInfo Entity에는 오직 하나의 Row만 존재할 수 있음
}