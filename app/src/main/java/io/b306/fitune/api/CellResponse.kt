package io.b306.fitune.api

import io.b306.fitune.model.BattleUserData
import io.b306.fitune.model.User
import io.b306.fitune.model.UserData

data class CellResponse(
    val message: String,
    val data : List<BattleUserData>
)
