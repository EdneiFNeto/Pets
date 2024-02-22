package com.pets.ui.viewmodel.login

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pets.data.model.Users
import com.pets.utils.PreferencesService
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
        Log.d(TAG, "fromJson $fromJson")
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

    private fun onExecuteLogin(email: String, navHostController: NavHostController) {
        Log.d("onExecuteLogin", "Execute login")

        try {
            val filterUsers = uiState.users.filter { it.email == email }

            if (filterUsers.isEmpty()) {
                throw Exception("Usuário não encontrado!")
            }

            Handler(Looper.getMainLooper()).postDelayed({
                preferences.setEmail(email)
                navHostController.navigate(MainScreen.Category.route)
            }, 2000)

        } catch (e: Exception) {
            e.printStackTrace()
            uiState = uiState.copy(
                status = LoginStatus.FAIL
            )
        }
    }

    private companion object {
        const val TAG = "LoginViewModel"
    }
}