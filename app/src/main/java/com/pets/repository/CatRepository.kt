package com.pets.repository

import com.pets.helpers.Resource
import com.pets.service.CatService
import com.pets.service.response.BreedsResponse
import com.pets.service.response.CatResponse
import javax.inject.Inject


class CatRepository @Inject constructor(
    private val service: CatService
): CatRepositoryInterface {
    override suspend fun list(page: Int, limit: Int): Resource<List<CatResponse>?> {
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