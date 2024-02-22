package com.pets.data.repository

import com.pets.utils.Resource
import com.pets.data.api.DogService
import com.pets.data.api.BreedsResponse
import com.pets.data.api.DogResponse
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val service: DogService
): DogRepositoryInterface {
    override suspend fun list(): Resource<List<DogResponse>?> {
        return try {
            Resource.returnResponse(service.list())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(data = null, msg = e.message ?: "")
        }
    }

    override suspend fun detail(id: String): Resource<BreedsResponse?> {
        return try {
            Resource.returnResponse(service.detail(id = id))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(data = null, msg = e.message ?: "")
        }
    }
}