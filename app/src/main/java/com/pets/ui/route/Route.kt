package com.pets.ui.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pets.ui.components.global.TopAppBarComponentState
import com.pets.ui.components.login.LoginComponent
import com.pets.ui.components.pets.CategoryPetsComponent
import com.pets.ui.components.pets.ListPetsComponent
import com.pets.ui.components.splashscreen.SplashScreenComponent
import com.pets.ui.viewmodel.login.LoginViewModel
import com.pets.ui.viewmodel.pets.PetsViewModel

@Composable
fun SplashRoute(
    navigate: NavHostController,
    topAppBarState: TopAppBarComponentState
) {
    SplashScreenComponent(navigate, topAppBarState)
}

@Composable
fun LoginRoute(
    navigate: NavHostController,
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
    navigate: NavHostController,
    topAppBarState: TopAppBarComponentState,
    viewModel: PetsViewModel = hiltViewModel()
) {
    CategoryPetsComponent(navigate, topAppBarState, handleEvent = viewModel::handleEvent)
}

@Composable
fun PetsRoute(
    navController: NavHostController,
    topAppBarState: TopAppBarComponentState,
    viewModel: PetsViewModel = hiltViewModel()
) {
    ListPetsComponent(
        navController,
        topAppBarState,
        handleEvent = viewModel::handleEvent,
        uiState = viewModel.uiState
    )
}


