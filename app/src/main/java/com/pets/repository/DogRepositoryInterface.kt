package com.pets.repository

import com.pets.helpers.Resource
import com.pets.service.response.DogResponse

interface DogRepositoryInterface {
    suspend fun list(page: Int, limit: Int): Resource<List<DogResponse>?>
}