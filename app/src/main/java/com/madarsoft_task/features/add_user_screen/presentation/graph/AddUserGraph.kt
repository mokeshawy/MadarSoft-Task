package com.madarsoft_task.features.add_user_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.add_user_screen.presentation.AddUserScreen
import com.madarsoft_task.features.users_list_screen.presentation.graph.navigateToUsersListGraph
import com.madarsoft_task.nav_host.RootGraph
import kotlinx.serialization.Serializable


@Serializable
data object AddUserScreen

fun NavGraphBuilder.addUserGraph(navController: NavController) {
    composable<AddUserScreen> {
        AddUserScreen(onRightIconClicked = navController::navigateToUsersListGraph)
    }
}


fun NavController.navigateToAddUserGraph() {
    navigate(route = AddUserScreen) {
        popUpTo(route = RootGraph) { inclusive = true }
    }
}