package com.pets.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.pets.R
import com.pets.preferences.PreferencesServiceImpl
import com.pets.ui.route.MainScreen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreenComponent(
    navigate: (MainScreen) -> Unit,
    topAppBarState: TopAppBarComponentState
) {
    topAppBarState.title.value = ""
    val context = LocalContext.current
    val preferences = PreferencesServiceImpl(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primaryColor)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieComponent(id = R.raw.ic_loading)

        LaunchedEffect(this) {
            delay(3.seconds)
            if (preferences.getEmail() != null) {
                navigate(MainScreen.Category)
            } else {
                navigate(MainScreen.Login)
            }
        }
    }
}
