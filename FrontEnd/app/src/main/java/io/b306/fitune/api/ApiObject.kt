package io.b306.fitune.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiObject {
    private const val BASE_URL = "https://j9b306.p.ssafy.io/v1/api/"

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // BODY는 요청/응답의 헤더 및 본문을 모두 로그에 출력합니다.
        }
    }

    // API 요청시 log 띄우기
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // API 요청
    private val getRetrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    // UserAPI 사용
    val getRetrofitService : UserAPI by lazy {
        getRetrofit.create(UserAPI::class.java)
    }

    val postRetrofitLoginService: LoginAPI by lazy {
        getRetrofit.create(LoginAPI::class.java)
    }

    val postRetrofitSignUpService: SignUpAPI by lazy {
        getRetrofit.create(SignUpAPI::class.java)
    }

    val patchRetrofitMyEditService: MyEditAPI by lazy {
        getRetrofit.create(MyEditAPI::class.java)
    }

    val patchRetrofitBattleService: BattleAPI by lazy {
        getRetrofit.create(BattleAPI::class.java)
    }

    val patchRetrofitExerciseService: ExerciseAPI by lazy {
        getRetrofit.create(ExerciseAPI::class.java)
    }

}

