package com.pets.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pets.R
import com.pets.viewmodel.Pet
import com.pets.viewmodel.PetsEvent
import com.pets.viewmodel.PetsStatus
import com.pets.viewmodel.PetsUiState

@Composable
fun ListPetsComponent(
    navigate: NavHostController,
    topAppBarState: TopAppBarComponentState,
    handleEvent: (PetsEvent) -> Unit,
    id: Int,
    uiState: PetsUiState
) {
    topAppBarState.apply {
        title.value = when (id) {
            Pet.CAT.id -> stringResource(id = R.string.title_list_cats)
            else -> stringResource(id = R.string.title_list_dogs)
        }

        navigationIcon.value = {
            IconButton(onClick = {
                when (uiState.status) {
                    PetsStatus.NONE -> handleEvent(PetsEvent.OnBackCategoryScreen(navigate))
                    PetsStatus.DETAIL -> handleEvent(PetsEvent.OnBackList)
                    else -> {}
                }
            }) {
                Icon(
                    Icons.Default.ArrowBack, null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        actions.value = {}
    }

    when (id) {
        Pet.DOG.id -> ListDog(uiState, id)
        else -> ListCat(uiState, id)
    }
}