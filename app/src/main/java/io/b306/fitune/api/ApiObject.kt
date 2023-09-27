package io.b306.fitune.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject {
    private const val BASE_URL = "http://j9b306.p.ssafy.io:5000/api/"

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // BODY는 요청/응답의 헤더 및 본문을 모두 로그에 출력합니다.
        }
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val getRetrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val getRetrofitService : UserAPI by lazy {
        getRetrofit.create(UserAPI::class.java)
    }
}

