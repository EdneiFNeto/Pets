package com.pets.ui.viewmodel.pets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.data.api.DogResponse
import com.pets.data.repository.DogRepositoryInterface
import com.pets.ui.route.MainScreen
import com.pets.utils.PreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val repository: DogRepositoryInterface,
    private val preferencesService: PreferencesService
) : ViewModel() {

    var uiState by mutableStateOf(PetsUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(status = PetsStatus.LOADER)
            selectPets()
        }
    }

    private suspend fun selectPets() {
        uiState = try {
            val response = repository.list()
            if (response.data == null) throw Exception()

            uiState.copy(
                dogResponse = response.data,
                status = PetsStatus.NONE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            uiState.copy(status = PetsStatus.FAIL)
        }
    }

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
                onDetailDog(event.dog)
            }

            PetsEvent.OnBackList -> {
                uiState = uiState.copy(status = PetsStatus.NONE)
            }
        }
    }

    private fun onDetailDog(dog: DogResponse) = viewModelScope.launch {
        uiState = try {
            val responseResource = repository.detail(dog.id ?: "")
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
}