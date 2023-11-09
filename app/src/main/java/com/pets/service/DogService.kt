package com.pets.service

import com.pets.service.response.BreedsResponse
import com.pets.service.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogService {

    @GET("v1/images/search")
    suspend fun list(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("has_breeds") has_breeds: Int = 1
    ): Response<List<DogResponse>>

    @GET("v1/images/{id}")
    suspend fun detail(
        @Path("id") id: String
    ): Response<BreedsResponse>
}