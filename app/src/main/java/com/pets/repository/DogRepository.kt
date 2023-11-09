package com.pets.repository

import com.pets.helpers.Resource
import com.pets.service.DogService
import com.pets.service.response.BreedsResponse
import com.pets.service.response.DogResponse
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val service: DogService
): DogRepositoryInterface {
    override suspend fun list(page: Int, limit: Int): Resource<List<DogResponse>?> {
        return try {
            Resource.returnResponse(service.list(page = page, limit = limit))
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