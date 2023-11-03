package com.pets.viewmodel

import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.logging.Handler
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val context
        get() = getApplication<Application>()

    var uiState by mutableStateOf(LoginUiState())
        private set

    init {
        readerJsonFile()
    }

    private fun readerJsonFile(): List<Users> {
        val jsonString = try {
            context.assets.open("user.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

        val listCountryType = object : TypeToken<List<Users>>() {}.type
        val fromJson = Gson().fromJson<List<Users>>(jsonString, listCountryType)
        Log.d("Reader", "fromJson $fromJson")
        uiState = uiState.copy(users = fromJson)
        return fromJson ?: arrayListOf()
    }

    fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnFail -> {}
            is LoginEvent.OnUpdateStatus -> {
                uiState = uiState.copy(status = event.status)
            }

            LoginEvent.OnSuccess -> {}
            is LoginEvent.OnLogin -> {
                viewModelScope.launch {
                    uiState = uiState.copy(status = LoginStatus.LOADER)
                    onExecuteLogin(event.email)
                }
            }
        }
    }

    private fun onExecuteLogin(email: String) {
        Log.d("onExecuteLogin", "Execute login")

        try {
            val filterUsers = uiState.users.filter { it.email == email }

            if (filterUsers.isEmpty()) {
                throw Exception("Usuário não encontrado!")
            }

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                uiState = uiState.copy(status = LoginStatus.SUCCESS)
            }, 3000)
        } catch (e: Exception) {
            e.printStackTrace()
            uiState = uiState.copy(
                status = LoginStatus.FAIL
            )
        }
    }
}

data class LoginUiState(
    val status: LoginStatus = LoginStatus.NONE,
    val users: List<Users> = arrayListOf(),
    val pets:List<Pets> = arrayListOf(Pets(name = "Jhon", age = 7))
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

data class Users(val email: String, val name: String)
data class Pets(val name: String, val age: Int)
