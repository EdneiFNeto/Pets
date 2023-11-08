package com.pets.service

import com.pets.service.request.CatRequest
import com.pets.service.response.CatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CatService {
    @POST("Commodity/Listar")
    suspend fun listar(@Body request: CatRequest): Response<List<CatResponse>>
}