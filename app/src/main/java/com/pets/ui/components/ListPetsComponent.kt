package com.pets.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pets.R
import com.pets.ui.route.MainScreen
import com.pets.viewmodel.PetsEvent
import com.pets.viewmodel.PetsUiState

@Composable
fun ListPetsComponent(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState,
    uiState: PetsUiState,
    handleEvent: (PetsEvent) -> Unit
) {
    topAppBarState.apply {
        title.value = stringResource(id = R.string.title_list_cats)
        navigationIcon.value = {
            IconButton(onClick = {
                handleEvent(PetsEvent.OnBackCategoryScreen(navigate))
            }) {
                Icon(
                    Icons.Default.ArrowBack, null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.primaryColor))
    ) {
        ListPets(uiState)
    }
}