package com.pets.service

import com.pets.service.response.BreedsResponse
import com.pets.service.response.CatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatService {
    @GET("v1/images/search")
    suspend fun list(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("has_breeds") has_breeds: Int = 1
    ): Response<List<CatResponse>>

    @GET("v1/images/{id}")
    suspend fun detail(
        @Path("id") id: String
    ): Response<BreedsResponse>
}