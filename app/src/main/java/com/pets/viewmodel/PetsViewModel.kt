package com.pets.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pets.helpers.CatPagination
import com.pets.helpers.DogPagination
import com.pets.preferences.PreferencesService
import com.pets.repository.CatRepositoryInterface
import com.pets.repository.DogRepositoryInterface
import com.pets.service.response.BreedsResponse
import com.pets.ui.route.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val dogRepositoryInterface: DogRepositoryInterface,
    private val catRepositoryInterface: CatRepositoryInterface,
    private val preferencesService: PreferencesService
) : ViewModel() {

    var uiState by mutableStateOf(PetsUiState())
        private set

    fun handleEvent(event: PetsEvent) {
        when (event) {
            is PetsEvent.OnBackCategoryScreen -> {
                event.navHostController.navigate(MainScreen.Category.route)
            }

            is PetsEvent.OnLogout -> {
                preferencesService.clear()
                event.navHostController.navigate(MainScreen.Login.route)
            }

            is PetsEvent.OnDetail -> {
                Log.d("PetsEvent", "OnDetail $event")
                viewModelScope.launch {
                    when(event.idPet) {
                        Pet.CAT.id ->  onDetailCats(event.id)
                        else -> onDetailDog(event.id)
                    }
                }
            }

            PetsEvent.OnBackList -> uiState = uiState.copy(status = PetsStatus.NONE)
        }
    }

    private suspend fun onDetailDog(id: String) {
        uiState = try {
            val responseResource = dogRepositoryInterface.detail(id)
            if (responseResource.data == null) throw Exception()

            uiState.copy(
                status = PetsStatus.DETAIL,
                breeds = responseResource.data
            )
        } catch (e: Exception) {
            e.printStackTrace()
            uiState.copy(status = PetsStatus.FAIL)
        }
    }

    private suspend fun onDetailCats(id: String) {
        uiState = try {
            val responseResource = catRepositoryInterface.detail(id)
            if (responseResource.data == null) throw Exception()

            uiState.copy(
                status = PetsStatus.DETAIL,
                breeds = responseResource.data
            )
        } catch (e: Exception) {
            e.printStackTrace()
            uiState.copy(status = PetsStatus.FAIL)
        }
    }

    val pageDog = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { DogPagination(repository = dogRepositoryInterface) }
    ).flow.cachedIn(viewModelScope)

    val pageCat = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { CatPagination(repository = catRepositoryInterface) }
    ).flow.cachedIn(viewModelScope)
}

data class PetsUiState(
    val status: PetsStatus = PetsStatus.NONE,
    val breeds: BreedsResponse? = null
)

enum class PetsStatus {
    NONE,
    DETAIL,
    FAIL
}

sealed class PetsEvent {
    data class OnBackCategoryScreen(val navHostController: NavHostController) : PetsEvent()
    data class OnLogout(val navHostController: NavHostController) : PetsEvent()
    data class OnDetail(val id: String, val idPet: Int) : PetsEvent()
    object OnBackList : PetsEvent()

}
