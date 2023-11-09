package com.pets.service

import com.pets.service.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {

    @GET("v1/images/search")
    suspend fun list(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<DogResponse>>
}