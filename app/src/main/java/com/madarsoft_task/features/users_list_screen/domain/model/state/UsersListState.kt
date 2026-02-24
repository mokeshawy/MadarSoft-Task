package com.madarsoft_task.features.users_list_screen.domain.model.state

import com.core.bases.base_viewmodel.ViewState
import com.core.error.AppError
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel

data class UsersListState(
    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val userUiModelList: List<UserUiModel> = emptyList(),
    val isUserDeleted : Boolean = false
) : ViewState
