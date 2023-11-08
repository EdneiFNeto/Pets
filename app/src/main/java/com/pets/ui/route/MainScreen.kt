package com.pets.ui.route

sealed class MainScreen(var title: String, var route: String) {
    object Home : MainScreen("Home", "home_route")
}