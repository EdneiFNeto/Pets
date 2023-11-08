package com.pets.service

import com.pets.service.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogService {

    @GET("v1/images/search?limit=10")
    suspend fun list(): Response<List<DogResponse>>
}