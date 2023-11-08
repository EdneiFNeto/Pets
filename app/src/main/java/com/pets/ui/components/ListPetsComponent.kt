package com.pets.ui.components

import androidx.compose.runtime.Composable
import com.pets.viewmodel.LoginUiState
import com.pets.viewmodel.Pet

@Composable
fun ListPetsComponent(uiState: LoginUiState) {
    when (uiState.pet) {
        Pet.DOG -> ListPets(uiState)
        Pet.CAT -> ListPets(uiState)
        else -> {}
    }
}