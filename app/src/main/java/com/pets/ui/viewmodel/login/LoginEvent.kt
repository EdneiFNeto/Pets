package com.pets.ui.viewmodel.login

import androidx.navigation.NavHostController

sealed class LoginEvent {
    data class OnUpdateStatus(val status: LoginStatus) : LoginEvent()
    data class OnLogin(val email: String, val navigate: NavHostController) : LoginEvent()
    object OnSuccess : LoginEvent()
    object OnFail : LoginEvent()
}
