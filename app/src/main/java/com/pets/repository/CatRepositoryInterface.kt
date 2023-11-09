package com.pets.repository

import com.pets.helpers.Resource
import com.pets.service.response.BreedsResponse
import com.pets.service.response.CatResponse

interface CatRepositoryInterface {
    suspend fun list(page: Int, limit: Int): Resource<List<CatResponse>?>
    suspend fun detail(id: String) : Resource<BreedsResponse?>
}
