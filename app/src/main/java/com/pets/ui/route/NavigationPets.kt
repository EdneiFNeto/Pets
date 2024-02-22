package com.pets.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pets.ui.components.global.TopAppBarComponentState

@Composable
fun Navigation(
    topAppBarState: TopAppBarComponentState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Splash.route
    ) {
        composable(MainScreen.Splash.route) {
            SplashRoute(navController, topAppBarState)
        }

        composable(MainScreen.Login.route) {
            LoginRoute(navController, topAppBarState)
        }

        composable(MainScreen.Category.route) {
            CategoryRoute(navController, topAppBarState)
        }

        composable(MainScreen.Pets.route) {
            PetsRoute(
                navController = navController,
                topAppBarState = topAppBarState,
            )
        }
    }
}