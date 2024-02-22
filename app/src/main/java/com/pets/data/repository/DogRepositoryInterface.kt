package com.pets.data.repository

import com.pets.utils.Resource
import com.pets.data.api.BreedsResponse
import com.pets.data.api.DogResponse

interface DogRepositoryInterface {
    suspend fun list(): Resource<List<DogResponse>?>
    suspend fun detail(id: String) : Resource<BreedsResponse?>
}