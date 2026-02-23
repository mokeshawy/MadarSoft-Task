package com.madarsoft_task.nav_host


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.madarsoft_task.features.add_user_screen.presentation.graph.addUserGraph
import com.madarsoft_task.features.splash_screen.graph.SplashScreen
import com.madarsoft_task.features.splash_screen.graph.splashGraph
import kotlinx.serialization.Serializable

@Serializable
data object RootGraph

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen::class,
        route = RootGraph::class
    ) {
        splashGraph(navController = navController)

        addUserGraph(navController = navController)
    }
}