package io.b306.fitune.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ExerciseAPI {
    @POST("record/{userSeq}")
    suspend fun postExerciseData(
        @Path("userSeq") userSeq: Int,
        @Body requestBody: ExerciseRequestBody
    ): Response<Void>
}