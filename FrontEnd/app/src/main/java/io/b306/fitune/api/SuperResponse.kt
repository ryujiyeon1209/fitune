package io.b306.fitune.api

import io.b306.fitune.model.MyInfoData
import io.b306.fitune.model.User

data class SuperResponse(
    val message: String,
    val data: MyInfoData
)
