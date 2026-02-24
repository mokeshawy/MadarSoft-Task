package com.madarsoft_task.features.splash_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.add_user_screen.presentation.graph.navigateToAddUserGraph
import com.madarsoft_task.features.splash_screen.presentation.SplashScreen
import kotlinx.serialization.Serializable


@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable<SplashScreen> {
        SplashScreen(onNavigateToAddUserScreen = navController::navigateToAddUserGraph)
    }
}