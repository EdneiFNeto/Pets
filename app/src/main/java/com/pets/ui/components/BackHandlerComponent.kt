package com.pets.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun BackHandlerComponent(callback:()-> Unit) {
    BackHandler(enabled = true, onBack = {
        callback()
    })
}