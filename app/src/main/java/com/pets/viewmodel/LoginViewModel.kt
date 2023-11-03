package com.pets.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

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
            is LoginEvent.OnLogin -> {
                viewModelScope.launch { onExecuteLogin(event.email) }
            }
        }
    }

    private fun onExecuteLogin(email: String) {
        Log.d("onExecuteLogin", "Execute login")

        uiState = try {
            val users = Gson().fromJson("", Array<Users>::class.java)
            val filterUsers = users.filter { it.email == email }

            if (filterUsers.isEmpty()) {
                throw Exception("Usuário não encontrado!")
            }

            uiState.copy(status = LoginStatus.SUCCESS)
        } catch (e: Exception) {
            e.printStackTrace()
            uiState.copy(
                status = LoginStatus.FAIL
            )
        }
    }
}

data class LoginUiState(
    val status: LoginStatus = LoginStatus.NONE
)

sealed class LoginEvent {
    data class OnUpdateStatus(val status: LoginStatus) : LoginEvent()
    data class OnLogin(val email: String) : LoginEvent()

    object OnSuccess : LoginEvent()
    object OnFail : LoginEvent()
}

enum class LoginStatus {
    NONE,
    LOADER,
    LOGIN,
    FAIL,
    SUCCESS
}

data class Users(val email: String, val password: String)
