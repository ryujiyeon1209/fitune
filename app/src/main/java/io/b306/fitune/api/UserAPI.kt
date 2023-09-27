package io.b306.fitune.api

import io.b306.fitune.api.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {
    @GET("user/info/{userId}")
    fun getUserInfo(@Path("userId") userId: Int) : Call<UserResponse>
}