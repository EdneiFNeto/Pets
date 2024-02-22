package com.pets.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogService {
    @GET("v1/images/search")
    suspend fun list(
        @Query("limit") limit: Int = 10,
        @Query("has_breeds") hasBreeds: Int = 1
    ): Response<List<DogResponse>>

    @GET("v1/images/{id}")
    suspend fun detail(
        @Path("id") id: String
    ): Response<BreedsResponse>
}