package io.b306.fitune.api

import io.b306.fitune.model.FightRecordData

data class FightRecordResponse (
    val message: String,
    val data : List<FightRecordData>
)