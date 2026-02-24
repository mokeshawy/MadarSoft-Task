package com.madarsoft_task.features.users_list_screen.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui_component.dialog_component.DialogComponent
import com.core.ui_component.failure_view.FailureView
import com.core.ui_component.main_top_bar.MainTopBar
import com.core.ui_component.ui_generic.GeneralLazyColumn
import com.madarsoft_task.R
import com.madarsoft_task.features.common.composable.UserItem
import com.madarsoft_task.features.common.composable.UserPopUpContent
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import com.madarsoft_task.features.users_list_screen.presentation.viewmodel.UsersListViewModel


@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel = hiltViewModel(),
    onNavigateToUserDetails: (UserUiModel) -> Unit,
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value
    val isUserListEmpty = uiState.userUiModelList.isEmpty()
    val isErrorOrEmpty = uiState.appError != null || uiState.userUiModelList.isEmpty()

    val errorText = if (isUserListEmpty) {
        stringResource(id = R.string.there_is_no_user_here)
    } else {
        stringResource(id = R.string.failed_to_load_content)
    }

    MainTopBar(
        title = R.string.users,
        isPullRefresh = false,
        leftIcon = R.drawable.ic_vector_back_arrow,
        onLeftIconClicked = onBackClicked,
        content = {
            when {
                isErrorOrEmpty -> FailureView(
                    tapText = R.string.tap_to_load_content,
                    errText = errorText,
                    icon = R.drawable.ic_vector_error,
                    onTapToRefresh = viewModel::refresh
                )

                else -> UsersListContent(
                    userUiModelList = uiState.userUiModelList,
                    onItemClicked = onNavigateToUserDetails,
                    onDeleteUser = { user -> viewModel.userDeleteSelected = user }
                )
            }
        })


    DeleteUserPopup(
        showDialog = viewModel.userDeleteSelected != null,
        name = viewModel.userDeleteSelected?.name ?: "-",
        onDeleteClicked = { viewModel.onDeleteUserClicked() },
        onCancel = { viewModel.onCancelDeleteUserClicked() }
    )

}

@Composable
fun UsersListContent(
    userUiModelList: List<UserUiModel>,
    onItemClicked: (UserUiModel) -> Unit,
    onDeleteUser: (UserUiModel) -> Unit
) {
    GeneralLazyColumn(
        modifier = Modifier.padding(all = 8.dp),
        list = userUiModelList
    ) { user ->
        UserItem(
            name = user.name ?: "-",
            age = user.age.toString(),
            jobTitle = user.jobTitle ?: "-",
            genderType = user.genderType ?: "-",
            onClicked = { onItemClicked(user) },
            onDeleteUser = { onDeleteUser(user) }
        )
    }
}


@Composable
fun DeleteUserPopup(
    showDialog: Boolean,
    name: String,
    onDeleteClicked: () -> Unit,
    onCancel: () -> Unit
) {
    DialogComponent(
        showDialog = showDialog,
        content = {
            UserPopUpContent(
                name = name,
                icon = R.drawable.ic_vector_delete,
                titleRes = R.string.are_you_sure_delete,
                leftBtnRes = R.string.delete,
                rightBtnRes = R.string.cancel,
                onLeftBtnClicked = onDeleteClicked,
                onRightBtnClicked = onCancel
            )
        })
}

@Composable
@Preview(showBackground = true)
fun OrderItemPreview() {
    UserItem(
        name = "Mohamed Keshawy",
        age = "38",
        jobTitle = "Senior Android Developer",
        genderType = "Male",
        onClicked = {},
        onDeleteUser = {}
    )
}


@Composable
@Preview(showBackground = true)
fun UserPopUpContentPreview() {
    UserPopUpContent(
        name = "Mohamed Keshawy",
        icon = R.drawable.ic_vector_delete,
        titleRes = R.string.are_you_sure_delete,
        leftBtnRes = R.string.delete,
        rightBtnRes = R.string.cancel,
        onLeftBtnClicked = { },
        onRightBtnClicked = {}
    )
}