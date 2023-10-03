package io.b306.fitune.api

import io.b306.fitune.api.UserResponse
import io.b306.fitune.model.MyInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {
    @GET("user/info/{userId}")
    fun getUserInfo(@Path("userId") userId: Int) : Call<UserResponse>

    @GET("battle/{userId}")
    fun getUserList(@Path("userId") userId: Int): Call<CellResponse>

    @GET("user/super/{email}")
    fun getMyInfo(@Path("email") email: String): Call<SuperResponse>
}