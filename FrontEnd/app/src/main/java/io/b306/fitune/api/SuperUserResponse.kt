package io.b306.fitune.api

import io.b306.fitune.model.FightResultData
import io.b306.fitune.model.User

data class SuperUserResponse(
    val message: String,
    val user: User,                             //사용자 정보
    val battleRecord: List<FightResultData>,    //대결 기록

)
