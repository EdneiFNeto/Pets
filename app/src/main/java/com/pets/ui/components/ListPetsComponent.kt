package com.pets.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavHostController
import com.pets.R
import com.pets.viewmodel.Pet
import com.pets.viewmodel.PetsEvent

@Composable
fun ListPetsComponent(
    navigate: NavHostController,
    topAppBarState: TopAppBarComponentState,
    handleEvent: (PetsEvent) -> Unit,
    id: Int
) {
    Log.d("ListPetsComponent", "ListPets: $id")

    topAppBarState.apply {
        title.value = when(id) {
            Pet.CAT.id -> stringResource(id = R.string.title_list_cats)
            else -> stringResource(id = R.string.title_list_dogs)
        }

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
        actions.value = {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryColor))
    ) {
        when(id) {
            Pet.DOG.id -> ListDog()
            else -> ListCat()
        }
    }
}