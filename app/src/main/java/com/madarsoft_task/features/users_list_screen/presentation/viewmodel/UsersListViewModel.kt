package com.madarsoft_task.features.users_list_screen.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.core.bases.base_viewmodel.BaseViewModel
import com.core.error.AppError
import com.core.extensions.viewModelScope
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import com.madarsoft_task.features.users_list_screen.domain.event.UsersListIntent
import com.madarsoft_task.features.users_list_screen.domain.mapper.toUserUiModel
import com.madarsoft_task.features.users_list_screen.domain.model.state.UsersListState
import com.madarsoft_task.features.users_list_screen.domain.repository.DeleteUserRepository
import com.madarsoft_task.features.users_list_screen.domain.repository.RetrieveUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val retrieveUsersRepository: RetrieveUsersRepository,
    private val deleteUserRepository: DeleteUserRepository
) : BaseViewModel<UsersListIntent, UsersListState>(initialState = UsersListState()) {

    var userDeleteSelected by mutableStateOf<UserUiModel?>(null)


    init {
        sendRetrieveUsersIntent()
    }

    fun refresh() = sendRetrieveUsersIntent()


    fun onDeleteUserClicked(){
        userDeleteSelected?.userId?.let {sendDeleteUerIntent(userId = it) }
        userDeleteSelected = null
    }

    fun onCancelDeleteUserClicked(){
        userDeleteSelected = null
    }

    private fun sendRetrieveUsersIntent() = sendIntent(UsersListIntent.RetrieveUsersIntent)


    private fun sendDeleteUerIntent(userId: Long) =
        sendIntent(UsersListIntent.DeleteUerIntent(userId = userId))


    override fun processIntent(intent: UsersListIntent) {
        when (intent) {
            UsersListIntent.RetrieveUsersIntent -> reduceUsersListState()
            is UsersListIntent.DeleteUerIntent -> reduceDeleteUserState(userId = intent.userId)
        }
    }


    private fun reduceUsersListState() = viewModelScope(context = Dispatchers.IO) {
        updateStateFlow { copy(isLoading = true) }
        try {
            retrieveUsersRepository.retrieveAllUsers().collect { users ->
                val usersUiModel = users.map { it.toUserUiModel() }
                updateStateFlow { copy(isLoading = false, userUiModel = usersUiModel) }
            }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateStateFlow { copy(isLoading = false, appError = appError) }
            }
        }
    }

    private fun reduceDeleteUserState(userId: Long) = viewModelScope(context = Dispatchers.IO) {
        updateStateFlow { copy(isLoading = true) }
        try {
            deleteUserRepository.deleteUser(userId = userId)
            updateStateFlow { copy(isLoading = false, isUserDeleted = true) }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateStateFlow { copy(isLoading = false, appError = appError) }
            }
        }
    }
}