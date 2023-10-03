package io.b306.fitune.api

import retrofit2.Response
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

public interface MyEditAPI {
    @PATCH("user/edit/cell/{userSeq}/{cellName}")
    suspend fun updateCellName(@Path("userSeq") userSeq: Int, @Path("cellName") cellName: String): Response<Void>

    @PATCH("user/edit/height/{userSeq}/{height}")
    suspend fun updateHeight(@Path("userSeq") userSeq: Int, @Path("height") height: Int): Response<Void>

    @POST("user/edit/prefer/{userSeq}/{preferSeq}")
    suspend fun updatePrefer(@Path("userSeq") userSeq: Int, @Path("preferSeq") preferSeq: Int): Response<Void>

    @PATCH("user/edit/tension/{userSeq}/{tension}")
    suspend fun updateTension(@Path("userSeq") userSeq: Int, @Path("tension") tension: Int): Response<Void>

    @PATCH("user/edit/weight/{userSeq}/{weight}")
    suspend fun updateWeight(@Path("userSeq") userSeq: Int, @Path("weight") weight: Int): Response<Void>
}