package com.pets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnFail -> {}
            is LoginEvent.OnUpdateStatus -> {
                uiState = uiState.copy(status = event.status)
            }

            LoginEvent.OnSuccess -> {}
        }
    }
}

data class LoginUiState(
    val status: LoginStatus = LoginStatus.NONE
)

sealed class LoginEvent {
    data class OnUpdateStatus(val status: LoginStatus) : LoginEvent()
    object OnSuccess : LoginEvent()
    object OnFail : LoginEvent()
}

enum class LoginStatus {
    NONE,
    LOADER,
    GO_TO_LOGIN
}
