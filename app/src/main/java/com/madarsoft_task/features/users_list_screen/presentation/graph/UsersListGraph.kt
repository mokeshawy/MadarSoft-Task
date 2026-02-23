package com.madarsoft_task.features.users_list_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.add_user_screen.presentation.graph.AddUserScreen
import com.madarsoft_task.features.users_list_screen.presentation.UsersListScreen
import kotlinx.serialization.Serializable

@Serializable
data object UsersListScreen

fun NavGraphBuilder.usersListGraph(navController: NavController) {
    composable<UsersListScreen> {
        UsersListScreen(
            onNavigateToUserDetails = {
                //TODO HANDLE NAVIGATE TO USER DETAILS HERE
            },
            onBackClicked = navController::popBackStack
        )
    }
}


fun NavController.navigateToUsersListGraph() {
    navigate(route = UsersListScreen) {
        popUpTo(route = AddUserScreen) { inclusive = false }
    }
}