package com.pets.ui.route

sealed class MainScreen(var title: String, var route: String) {
    object Splash : MainScreen("Splash", "splash_route")
    object Login : MainScreen("Login", "login_route")
    object Category : MainScreen("Category", "category_route")
    object Pets : MainScreen("Pets", "pets_route")
}