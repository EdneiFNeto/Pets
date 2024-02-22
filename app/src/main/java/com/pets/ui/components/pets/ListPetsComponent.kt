package com.pets.ui.components.pets

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
import com.pets.ui.components.global.TopAppBarComponentState
import com.pets.ui.components.pets.dog.ListDog
import com.pets.ui.viewmodel.pets.PetsEvent
import com.pets.ui.viewmodel.pets.PetsStatus
import com.pets.ui.viewmodel.pets.PetsUiState

@Composable
fun ListPetsComponent(
    navigate: NavHostController,
    topAppBarState: TopAppBarComponentState,
    handleEvent: (PetsEvent) -> Unit,
    uiState: PetsUiState,
) {
    topAppBarState.apply {
        title.value = stringResource(id = R.string.title_list_dogs)
        navigationIcon.value = {
            IconButton(onClick = {
                when (uiState.status) {
                    PetsStatus.NONE -> handleEvent(PetsEvent.OnBackCategoryScreen(navigate))
                    else -> handleEvent(PetsEvent.OnBackList)
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
    ListDog(uiState, handleEvent)
}