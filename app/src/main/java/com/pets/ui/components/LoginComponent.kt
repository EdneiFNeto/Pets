package com.pets.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.pets.R
import com.pets.ui.route.MainScreen
import com.pets.viewmodel.LoginEvent
import com.pets.viewmodel.LoginStatus
import com.pets.viewmodel.LoginUiState

@Composable
fun LoginComponent(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState,
    uiState: LoginUiState,
    handleEvent: (LoginEvent) -> Unit
) {

    topAppBarState.apply {
        title.value = ""
        navigationIcon.value = {}
        actions.value = {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primaryColor)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState.status) {
            LoginStatus.LOGIN -> FormLoginComponent(handleEvent, navigate)
            LoginStatus.LOADER,
            LoginStatus.FAIL -> LoaderComponent(uiState, handleEvent)
        }
    }
}