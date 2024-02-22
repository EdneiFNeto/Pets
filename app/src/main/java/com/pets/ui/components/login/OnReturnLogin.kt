package com.pets.ui.components.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.pets.ui.viewmodel.login.LoginEvent
import com.pets.ui.viewmodel.login.LoginStatus
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun OnReturnLogin(
    handleEvent: (event: LoginEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5.seconds)
        handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.LOGIN))
    }
}