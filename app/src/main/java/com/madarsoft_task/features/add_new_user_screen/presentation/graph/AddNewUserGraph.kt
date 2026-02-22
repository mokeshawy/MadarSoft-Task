package com.madarsoft_task.features.add_new_user_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.add_new_user_screen.presentation.AddNewUserScreen
import com.madarsoft_task.nav_host.RootGraph
import kotlinx.serialization.Serializable


@Serializable
data object AddNewUserScreen

fun NavGraphBuilder.addNewUserGraph(navController: NavController) {
    composable<AddNewUserScreen> {
        AddNewUserScreen(onRightIconClicked = {
            //TODO HANDLE NAVIGATE TO USER USER LIST SCREEN
        })
    }
}


fun NavController.navigateToAddNewUserGraph() {
    navigate(route = AddNewUserScreen) {
        popUpTo(route = RootGraph) { inclusive = true }
    }
}