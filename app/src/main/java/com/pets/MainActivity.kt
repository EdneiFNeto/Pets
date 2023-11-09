package com.pets

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pets.ui.components.TopAppBarComponent
import com.pets.ui.components.TopAppBarComponentState
import com.pets.ui.components.rememberTopAppBarState
import com.pets.ui.route.CategoryRoute
import com.pets.ui.route.LoginRoute
import com.pets.ui.route.MainScreen
import com.pets.ui.route.PetsRoute
import com.pets.ui.route.SplashRoute
import com.pets.ui.theme.PetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }
        }
    }
}

@Composable
private fun MainScreenView() {
    val navController = rememberNavController()
    val topAppBarState = rememberTopAppBarState(title = MainScreen.Login.title)

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStack ->
            backStack.destination.route?.let { route ->
                Log.i("HomeActivityLog", route)
            }
        }
    }

    Scaffold(
        topBar = { TopAppBarComponent(state = topAppBarState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Navigation(topAppBarState)
            }
        }
    )
}

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

        composable(
            "${MainScreen.Pets.route}/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->
            PetsRoute(
                navController = navController,
                topAppBarState = topAppBarState,
                id = backStackEntry.arguments?.getInt("petId") ?: 0
            )
        }
    }
}