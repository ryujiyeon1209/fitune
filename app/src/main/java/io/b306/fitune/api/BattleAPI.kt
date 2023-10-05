package io.b306.fitune.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BattleAPI {
    @POST("battle/add")
    suspend fun addBattle(@Body request: BattleAddRequest): Response<BattleAddResponse>
}