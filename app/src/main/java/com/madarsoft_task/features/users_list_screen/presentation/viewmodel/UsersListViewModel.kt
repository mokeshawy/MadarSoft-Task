package com.madarsoft_task.features.users_list_screen.presentation.viewmodel

import com.core.bases.base_viewmodel.BaseViewModel
import com.core.error.AppError
import com.core.extensions.viewModelScope
import com.madarsoft_task.features.users_list_screen.domain.event.UsersListIntent
import com.madarsoft_task.features.users_list_screen.domain.mapper.toUserUiModel
import com.madarsoft_task.features.users_list_screen.domain.model.state.UsersListState
import com.madarsoft_task.features.users_list_screen.domain.repository.RetrieveUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(private val retrieveUsersRepository: RetrieveUsersRepository) :
    BaseViewModel<UsersListIntent, UsersListState>(initialState = UsersListState()) {

    init {
        sendRetrieveUsersIntent()
    }

    fun refresh() = sendRetrieveUsersIntent()

    private fun sendRetrieveUsersIntent() = sendIntent(UsersListIntent.RetrieveUsersIntent)

    override fun processIntent(intent: UsersListIntent) {
        when (intent) {
            UsersListIntent.RetrieveUsersIntent -> reduceUsersListState()
        }
    }


    private fun reduceUsersListState() = viewModelScope {
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
}