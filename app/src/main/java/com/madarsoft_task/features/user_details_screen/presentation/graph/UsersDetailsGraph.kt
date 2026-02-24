package com.madarsoft_task.features.user_details_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import com.madarsoft_task.features.user_details_screen.presentation.UserDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class UsersDetailsScreen(
    val userId: Long,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val genderType: String
)

fun NavGraphBuilder.usersDetailsGraph(navController: NavController) {
    composable<UsersDetailsScreen> {
        UserDetailsScreen(onBackClicked = navController::popBackStack)
    }
}


fun NavController.navigateToUsersDetailsGraph(model: UserUiModel) {
    val usersModel = UsersDetailsScreen(
        userId = model.userId ?: 0L,
        name = model.name ?: "",
        age = model.age ?: 0,
        jobTitle = model.jobTitle ?: "",
        genderType = model.genderType ?: ""
    )
    navigate(route = usersModel) {
        restoreState = true
    }
}