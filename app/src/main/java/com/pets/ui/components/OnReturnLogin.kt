package com.pets.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.pets.viewmodel.LoginEvent
import com.pets.viewmodel.LoginStatus
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