package com.madarsoft_task.features.add_user_screen.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.core.bases.base_viewmodel.BaseViewModel
import com.core.error.AppError
import com.core.extensions.viewModelScope
import com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent
import com.madarsoft_task.features.add_user_screen.domain.model.state.AddUserState
import com.madarsoft_task.features.add_user_screen.domain.repository.AddUserRepository
import com.madarsoft_task.features.add_user_screen.domain.usecase.validations_usecase.AgeValidationUseCase
import com.madarsoft_task.features.add_user_screen.domain.usecase.validations_usecase.GenderTypeValidationUseCase
import com.madarsoft_task.features.add_user_screen.domain.usecase.validations_usecase.JobTitleValidationUseCase
import com.madarsoft_task.features.add_user_screen.domain.usecase.validations_usecase.NameValidationUseCase
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val nameValidationUseCase: NameValidationUseCase,
    private val ageValidationUseCase: AgeValidationUseCase,
    private val jobTitleValidationUseCase: JobTitleValidationUseCase,
    private val genderTypeValidationUseCase: GenderTypeValidationUseCase,
    private val addUserRepository: AddUserRepository
) : BaseViewModel<AddUserIntent, AddUserState>(initialState = AddUserState()) {


    var showAddUserSuccessPopup by mutableStateOf(false)

    fun sendNameValidationIntent(name: String) =
        sendIntent(AddUserIntent.NameValidationIntent(name = name))

    fun sendAgeValidationIntent(age: String) =
        sendIntent(AddUserIntent.AgeValidationIntent(age = age))

    fun sendJobTitleValidationIntent(jobTitle: String) =
        sendIntent(AddUserIntent.JobTitleValidationIntent(jobTitle = jobTitle))

    fun sendGenderValidationIntent(genderType: String) =
        sendIntent(AddUserIntent.GenderValidationIntent(genderType = genderType))


    fun sendAddUserIntent() =
        sendIntent(AddUserIntent.AddUserIntent(userUiModel = userUiModel))

    private val userUiModel
        get() = UserUiModel(
            name = uiStateFlow.value.name,
            age = uiStateFlow.value.age.toInt(),
            jobTitle = uiStateFlow.value.jobTitle,
            genderType = uiStateFlow.value.genderType
        )


    override fun processIntent(intent: AddUserIntent) {
        when (intent) {
            is AddUserIntent.NameValidationIntent -> {
                reduceNameValidationState(name = intent.name)
                isNameValid()
            }

            is AddUserIntent.AgeValidationIntent -> {
                reduceAgeValidationState(age = intent.age)
                isAgeValid()
            }

            is AddUserIntent.JobTitleValidationIntent -> {
                reduceJobTitleValidationState(jobTitle = intent.jobTitle)
                isJobTitleValid()
            }

            is AddUserIntent.GenderValidationIntent -> {
                reduceGenderTypeValidationState(genderType = intent.genderType)
                isGenderTypeValid()
            }

            is AddUserIntent.AddUserIntent -> reduceAddUserState(userUiModel = intent.userUiModel)
        }
    }


    private fun reduceNameValidationState(name: String) = updateStateFlow { copy(name = name) }

    private fun isNameValid(): Boolean {
        val validationState = AddUserState(name = uiStateFlow.value.name)
        val result = nameValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(nameError = result.errorMessage) }
        return result.isSuccessful
    }


    private fun reduceAgeValidationState(age: String) = updateStateFlow { copy(age = age) }

    private fun isAgeValid(): Boolean {
        val validationState = AddUserState(age = uiStateFlow.value.age)
        val result = ageValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(ageError = result.errorMessage) }
        return result.isSuccessful
    }


    private fun reduceJobTitleValidationState(jobTitle: String) =
        updateStateFlow { copy(jobTitle = jobTitle) }

    private fun isJobTitleValid(): Boolean {
        val validationState = AddUserState(jobTitle = uiStateFlow.value.jobTitle)
        val result = jobTitleValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(jobTitleError = result.errorMessage) }
        return result.isSuccessful
    }

    private fun reduceGenderTypeValidationState(genderType: String) =
        updateStateFlow { copy(genderType = genderType) }

    private fun isGenderTypeValid(): Boolean {
        val validationState = AddUserState(genderType = uiStateFlow.value.genderType)
        val result = genderTypeValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(genderTypeError = result.errorMessage) }
        return result.isSuccessful
    }

    val isAddUserBtnEnabled get() = isNameValid() && isAgeValid() && isJobTitleValid() && isGenderTypeValid()


    private fun reduceAddUserState(userUiModel: UserUiModel) = viewModelScope(
        context = Dispatchers.IO
    ) {
        updateStateFlow { copy(isLoading = true) }
        try {
            addUserRepository.addUser(user = userUiModel.toUserEntity())
            updateStateFlow { copy(isLoading = false, isAddedSuccess = true) }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateStateFlow { copy(isLoading = false, appError = appError) }
            }
        }
    }


    fun onDismissSuccessfulAddUserPopup(){
        showAddUserSuccessPopup = false
        resetAddUserState()
    }

    fun resetAddUserState() = updateStateFlow {
        copy(
            name = "",
            age = "",
            jobTitle = "",
            genderType = "",
            isLoading = false,
            appError = null,
            isAddedSuccess = false
        )
    }
}