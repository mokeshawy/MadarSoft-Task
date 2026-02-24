package com.madarsoft_task.nav_host


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.madarsoft_task.features.add_user_screen.presentation.graph.addUserGraph
import com.madarsoft_task.features.splash_screen.presentation.graph.SplashScreen
import com.madarsoft_task.features.splash_screen.presentation.graph.splashGraph
import com.madarsoft_task.features.user_details_screen.presentation.graph.usersDetailsGraph
import com.madarsoft_task.features.users_list_screen.presentation.graph.usersListGraph
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

        usersListGraph(navController = navController)

        usersDetailsGraph(navController = navController)
    }
}