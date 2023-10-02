package io.b306.fitune.api;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {
    @POST("/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}