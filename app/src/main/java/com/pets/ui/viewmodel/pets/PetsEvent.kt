package com.pets.ui.viewmodel.pets

import androidx.navigation.NavHostController
import com.pets.data.api.DogResponse

sealed class PetsEvent {
    data class OnBackCategoryScreen(val navHostController: NavHostController) : PetsEvent()
    data class OnLogout(val navHostController: NavHostController) : PetsEvent()
    data class OnDetail(val dog: DogResponse) : PetsEvent()
    object OnBackList : PetsEvent()

}
