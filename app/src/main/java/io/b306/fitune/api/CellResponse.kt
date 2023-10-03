package io.b306.fitune.api

import io.b306.fitune.model.BattleUserData

data class CellResponse(
    val message: String,
    val data : List<BattleUserData>
)
