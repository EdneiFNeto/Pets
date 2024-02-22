package com.pets.ui.viewmodel.pets

import com.pets.data.api.BreedsResponse
import com.pets.data.api.DogResponse

data class PetsUiState(
    val status: PetsStatus = PetsStatus.NONE,
    val breeds: BreedsResponse? = null,
    val dogResponse: List<DogResponse> = arrayListOf(),
)
