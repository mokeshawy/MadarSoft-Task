package com.madarsoft_task.features.user_details_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.core.ui_component.main_top_bar.MainTopBar
import com.madarsoft_task.R
import com.madarsoft_task.features.common.composable.UserItem
import com.madarsoft_task.features.user_details_screen.presentation.viewmodel.UserDetailsViewModel


@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    val userModel = viewModel.userModel

    MainTopBar(
        title = R.string.app_name,
        titleText = stringResource(id = R.string.string_placeholder, userModel.name),
        isPullRefresh = false,
        leftIcon = R.drawable.ic_vector_back_arrow,
        onLeftIconClicked = onBackClicked,
        content = {
            UserDetailsContent(
                name = userModel.name,
                age = userModel.age.toString(),
                jobTitle = userModel.jobTitle,
                genderType = userModel.genderType,
            )
        }
    )
}

@Composable
fun UserDetailsContent(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    jobTitle: String,
    genderType: String,
) {
    Box(modifier = modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
        UserItem(
            name = name,
            age = age,
            jobTitle = jobTitle,
            genderType = genderType,
            isShowDeleteIcon = false
        )
    }
}