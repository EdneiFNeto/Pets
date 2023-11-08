package com.pets.ui.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.pets.ui.components.CategoryPetsComponent
import com.pets.ui.components.ListPetsComponent
import com.pets.ui.components.LoginComponent
import com.pets.ui.components.SplashScreenComponent
import com.pets.ui.components.TopAppBarComponentState
import com.pets.viewmodel.LoginViewModel
import com.pets.viewmodel.PetsViewModel

@Composable
fun SplashRoute(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState
) {
    SplashScreenComponent(navigate, topAppBarState)
}

@Composable
fun LoginRoute(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginComponent(
        navigate,
        topAppBarState,
        uiState = viewModel.uiState,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun CategoryRoute(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState,
    viewModel: PetsViewModel = hiltViewModel()
) {
    CategoryPetsComponent(navigate, topAppBarState, handleEvent = viewModel::handleEvent)
}

@Composable
fun PetsRoute(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState,
    viewModel: PetsViewModel = hiltViewModel()
) {
    ListPetsComponent(
        navigate,
        topAppBarState,
        uiState = viewModel.uiState,
        handleEvent = viewModel::handleEvent
    )
}


