package com.pets.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.pets.R
import com.pets.viewmodel.LoginEvent
import com.pets.viewmodel.LoginStatus
import com.pets.viewmodel.LoginUiState
import com.pets.viewmodel.Pet

@Composable
fun MainComponent(
    topAppBarState: TopAppBarComponentState,
    uiState: LoginUiState,
    handleEvent: (LoginEvent) -> Unit
) {

    topAppBarState.apply {
        title.value = when(uiState.status) {
            LoginStatus.CATEGORY_PETS ->  stringResource(id = R.string.title_select_pets)
            LoginStatus.LIST_PETS ->  if(uiState.pet == Pet.DOG)
                stringResource(id = R.string.title_list_dogs)
            else  stringResource(id = R.string.title_list_cats)
            else -> ""
        }
        visibility.value = uiState.status == LoginStatus.CATEGORY_PETS
                || uiState.status == LoginStatus.LIST_PETS
        navigationIcon.value = {
            when (uiState.status) {
                LoginStatus.LIST_PETS -> {
                    IconButton(onClick = {
                        handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.CATEGORY_PETS))
                    }) {
                        Icon(Icons.Filled.ArrowBack, "arrow back", tint = Color.White)
                    }
                }

                else -> {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, "home", tint = Color.White)
                    }
                }
            }
        }

        actions.value = {
            if(uiState.status == LoginStatus.CATEGORY_PETS) {
                IconButton(onClick = {
                    handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.LOGIN))
                }) {
                    Icon(Icons.Filled.ExitToApp, "Exit", tint = Color.White)
                }
            }
        }
    }

    BackHandlerComponent {
        when (uiState.status) {
            LoginStatus.LIST_PETS -> {
                handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.CATEGORY_PETS))
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primaryColor)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState.status) {
            LoginStatus.NONE -> SplashScreenComponent(handleEvent)
            LoginStatus.LOGIN -> LoginComponent(handleEvent)
            LoginStatus.CATEGORY_PETS -> CategoryPetsComponent(handleEvent)
            LoginStatus.LIST_PETS -> ListPetsComponent(uiState)
            LoginStatus.LOADER,
            LoginStatus.FAIL -> LoaderComponent(uiState, handleEvent)
        }
    }
}