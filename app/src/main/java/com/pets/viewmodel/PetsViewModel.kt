package com.pets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.preferences.PreferencesService
import com.pets.repository.DogRepositoryInteface
import com.pets.service.response.DogResponse
import com.pets.ui.route.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val repository: DogRepositoryInteface,
    private val preferencesService: PreferencesService
) : ViewModel() {

    var uiState by mutableStateOf(PetsUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(
                dogs = repository.list().data ?: arrayListOf()
            )
        }
    }

    fun handleEvent(event: PetsEvent) {
        when(event) {
            is PetsEvent.OnBackCategoryScreen -> {
                event.navigate(MainScreen.Category)
            }

            is PetsEvent.OnLogout -> {
                preferencesService.clear()
                event.navigate(MainScreen.Login)
            }
        }
    }
}

data class PetsUiState(
    val dogs: List<DogResponse> = arrayListOf(),
    val cats: List<Pets> = arrayListOf(),
)

sealed class PetsEvent {
    data class OnBackCategoryScreen(val navigate: (MainScreen) -> Unit): PetsEvent()
    data class OnLogout(val navigate: (MainScreen) -> Unit): PetsEvent()
}