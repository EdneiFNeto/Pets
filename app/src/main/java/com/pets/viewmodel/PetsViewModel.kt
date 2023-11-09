package com.pets.viewmodel

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
import com.pets.ui.route.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val dogRepositoryInterface: DogRepositoryInterface,
    private val catRepositoryInterface: CatRepositoryInterface,
    private val preferencesService: PreferencesService
) : ViewModel() {

    fun handleEvent(event: PetsEvent) {
        when (event) {
            is PetsEvent.OnBackCategoryScreen -> {
                event.navHostController.navigate(MainScreen.Category.route)
            }

            is PetsEvent.OnLogout -> {
                preferencesService.clear()
                event.navHostController.navigate(MainScreen.Login.route)
            }
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

sealed class PetsEvent {
    data class OnBackCategoryScreen(val navHostController: NavHostController) : PetsEvent()
    data class OnLogout(val navHostController: NavHostController) : PetsEvent()
}
