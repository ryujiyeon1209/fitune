package io.b306.fitune.api;

import io.b306.fitune.model.SignUpUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpAPI {
    @POST("user/create")
    Call<signUpResponse> createUser(@Body SignUpUser signUpUser);
}