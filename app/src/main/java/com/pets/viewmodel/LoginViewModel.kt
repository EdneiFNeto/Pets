package com.pets.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pets.preferences.PreferencesService
import com.pets.ui.route.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferences: PreferencesService,
    application: Application,
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
                    onExecuteLogin(event.email, event.navigate)
                }
            }
        }
    }

    private fun onExecuteLogin(email: String, navigate: (MainScreen) -> Unit) {
        Log.d("onExecuteLogin", "Execute login")

        try {
            val filterUsers = uiState.users.filter { it.email == email }

            if (filterUsers.isEmpty()) {
                throw Exception("Usuário não encontrado!")
            }

            Handler(Looper.getMainLooper()).postDelayed({
                preferences.setEmail(email)
                navigate(MainScreen.Category)
            }, 2000)

        } catch (e: Exception) {
            e.printStackTrace()
            uiState = uiState.copy(
                status = LoginStatus.FAIL
            )
        }
    }
}

data class LoginUiState(
    val status: LoginStatus = LoginStatus.LOGIN,
    val users: List<Users> = arrayListOf(),
    val pet: Pet? = null
)

sealed class LoginEvent {
    data class OnUpdateStatus(val status: LoginStatus) : LoginEvent()
    data class OnLogin(val email: String, val navigate: (MainScreen) -> Unit) : LoginEvent()
    object OnSuccess : LoginEvent()
    object OnFail : LoginEvent()
}

enum class LoginStatus {
    LOADER,
    LOGIN,
    FAIL
}

enum class Pet {
    DOG,
    CAT
}

data class Users(val email: String, val name: String)

data class Pets(val name: String, val age: Int)
