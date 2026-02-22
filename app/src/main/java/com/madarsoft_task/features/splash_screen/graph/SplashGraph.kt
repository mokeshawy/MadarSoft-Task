package com.madarsoft_task.features.splash_screen.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.add_new_user_screen.presentation.graph.navigateToAddNewUserGraph
import com.madarsoft_task.features.splash_screen.presentation.SplashScreen
import kotlinx.serialization.Serializable


@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable<SplashScreen> {
        SplashScreen(onNavigateToAddNewUserScreen = navController::navigateToAddNewUserGraph)
    }
}