package com.pets.repository

import com.pets.helpers.Resource
import com.pets.service.response.DogResponse

interface DogRepositoryInteface {
    suspend fun list(): Resource<List<DogResponse>?>
}