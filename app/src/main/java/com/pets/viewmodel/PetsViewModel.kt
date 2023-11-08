package com.pets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.pets.preferences.PreferencesService
import com.pets.repository.DogRepositoryInteface
import com.pets.service.response.DogResponse
import com.pets.ui.route.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val repository: DogRepositoryInteface,
    private val preferencesService: PreferencesService
) : ViewModel() {

    var uiState by mutableStateOf(PetsUiState())
        private set

    fun handleEvent(event: PetsEvent) {
        when (event) {
            is PetsEvent.OnBackCategoryScreen -> {
                event.navigate(MainScreen.Category)
            }

            is PetsEvent.OnLogout -> {
                preferencesService.clear()
                event.navigate(MainScreen.Login)
            }
        }
    }

    val pager = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { MyPagingSource(repository = repository) }
    ).flow.cachedIn(viewModelScope)
}

data class PetsUiState(
    val dogs: List<DogResponse> = arrayListOf(),
    val cats: List<Pets> = arrayListOf(),
)

sealed class PetsEvent {
    data class OnBackCategoryScreen(val navigate: (MainScreen) -> Unit) : PetsEvent()
    data class OnLogout(val navigate: (MainScreen) -> Unit) : PetsEvent()
}

class MyPagingSource(
    private val repository: DogRepositoryInteface,
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
