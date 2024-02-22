package com.pets.ui.viewmodel.login

import com.pets.data.model.Users
import com.pets.ui.viewmodel.pets.Pet

data class LoginUiState(
    val status: LoginStatus = LoginStatus.LOGIN,
    val users: List<Users> = arrayListOf(),
    val pet: Pet? = null
)
