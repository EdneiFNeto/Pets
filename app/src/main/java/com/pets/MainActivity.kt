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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pets.ui.components.TopAppBarComponent
import com.pets.ui.components.TopAppBarComponentState
import com.pets.ui.components.rememberTopAppBarState
import com.pets.ui.route.MainRoute
import com.pets.ui.route.MainScreen
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
    val topAppBarState = rememberTopAppBarState(title = MainScreen.Home.title)

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
                Navigation(navController, topAppBarState)
            }
        }
    )
}

@Composable
fun Navigation(
    navController: NavHostController,
    topAppBarState: TopAppBarComponentState
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.Home.route
    ) {
        composable(MainScreen.Home.route) { MainRoute(topAppBarState) }
    }
}