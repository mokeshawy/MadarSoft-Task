package com.madarsoft_task.features.users_list_screen.domain.event

import com.core.bases.base_viewmodel.ViewIntent

sealed class UsersListIntent : ViewIntent {
    data object RetrieveUsersIntent : UsersListIntent()

    data class DeleteUerIntent(val userId: Long) : UsersListIntent()
}