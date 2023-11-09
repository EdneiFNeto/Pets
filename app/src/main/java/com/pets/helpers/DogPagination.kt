package com.pets.helpers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pets.repository.DogRepositoryInterface
import com.pets.service.response.DogResponse
import retrofit2.HttpException
import java.io.IOException

class DogPagination(
    private val repository: DogRepositoryInterface,
) : PagingSource<Int, DogResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DogResponse> {
        return try {
            val prev = params.key ?: 1
            val response = repository.list(page = prev, limit = params.loadSize)

            if (response.data != null) {
                LoadResult.Page(
                    data = response.data,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (response.data.size < params.loadSize) null else prev + 1
                )
            } else {
                LoadResult.Error(Exception(""))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, DogResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}