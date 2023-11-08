package com.pets.ui.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.pets.ui.components.MainComponent
import com.pets.ui.components.TopAppBarComponentState
import com.pets.viewmodel.LoginViewModel

@Composable
fun MainRoute(
    topAppBarState: TopAppBarComponentState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    MainComponent(topAppBarState, uiState = viewModel.uiState, handleEvent = viewModel::handleEvent)
}
