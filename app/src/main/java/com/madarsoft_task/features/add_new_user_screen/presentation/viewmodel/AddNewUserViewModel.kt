package com.madarsoft_task.features.add_new_user_screen.presentation.viewmodel

import com.core.bases.base_viewmodel.BaseViewModel
import com.madarsoft_task.features.add_new_user_screen.domain.event.AddNewUserIntent
import com.madarsoft_task.features.add_new_user_screen.domain.model.state.AddNewUserState
import com.madarsoft_task.features.add_new_user_screen.domain.usecase.validations_usecase.AgeValidationUseCase
import com.madarsoft_task.features.add_new_user_screen.domain.usecase.validations_usecase.GenderTypeValidationUseCase
import com.madarsoft_task.features.add_new_user_screen.domain.usecase.validations_usecase.JobTitleValidationUseCase
import com.madarsoft_task.features.add_new_user_screen.domain.usecase.validations_usecase.NameValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewUserViewModel @Inject constructor(
    private val nameValidationUseCase: NameValidationUseCase,
    private val ageValidationUseCase: AgeValidationUseCase,
    private val jobTitleValidationUseCase: JobTitleValidationUseCase,
    private val genderTypeValidationUseCase: GenderTypeValidationUseCase
) : BaseViewModel<AddNewUserIntent, AddNewUserState>(initialState = AddNewUserState()) {


    fun sendNameValidationIntent(name: String) =
        sendIntent(AddNewUserIntent.NameValidationIntent(name = name))

    fun sendAgeValidationIntent(age: String) =
        sendIntent(AddNewUserIntent.AgeValidationIntent(age = age))

    fun sendJobTitleValidationIntent(jobTitle: String) =
        sendIntent(AddNewUserIntent.JobTitleValidationIntent(jobTitle = jobTitle))

    fun sendGenderValidationIntent(genderType: String) =
        sendIntent(AddNewUserIntent.GenderValidationIntent(genderType = genderType))


    override fun processIntent(intent: AddNewUserIntent) {
        when (intent) {
            is AddNewUserIntent.NameValidationIntent -> {
                reduceNameValidationState(name = intent.name)
                isNameValid()
            }

            is AddNewUserIntent.AgeValidationIntent -> {
                reduceAgeValidationState(age = intent.age)
                isAgeValid()
            }

            is AddNewUserIntent.JobTitleValidationIntent -> {
                reduceJobTitleValidationState(jobTitle = intent.jobTitle)
                isJobTitleValid()
            }

            is AddNewUserIntent.GenderValidationIntent -> {
                reduceGenderTypeValidationState(genderType = intent.genderType)
                isGenderTypeValid()
            }
        }
    }


    private fun reduceNameValidationState(name: String) = updateStateFlow { copy(name = name) }

    private fun isNameValid(): Boolean {
        val validationState = AddNewUserState(name = uiStateFlow.value.name)
        val result = nameValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(nameError = result.errorMessage) }
        return result.isSuccessful
    }


    private fun reduceAgeValidationState(age: String) = updateStateFlow { copy(age = age) }

    private fun isAgeValid(): Boolean {
        val validationState = AddNewUserState(age = uiStateFlow.value.age)
        val result = ageValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(ageError = result.errorMessage) }
        return result.isSuccessful
    }


    private fun reduceJobTitleValidationState(jobTitle: String) =
        updateStateFlow { copy(jobTitle = jobTitle) }

    private fun isJobTitleValid(): Boolean {
        val validationState = AddNewUserState(jobTitle = uiStateFlow.value.jobTitle)
        val result = jobTitleValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(jobTitleError = result.errorMessage) }
        return result.isSuccessful
    }

    private fun reduceGenderTypeValidationState(genderType: String) =
        updateStateFlow { copy(genderType = genderType) }

    private fun isGenderTypeValid(): Boolean {
        val validationState = AddNewUserState(genderType = uiStateFlow.value.genderType)
        val result = genderTypeValidationUseCase.execute(input = validationState)
        updateStateFlow { copy(genderTypeError = result.errorMessage) }
        return result.isSuccessful
    }

    val isAddUserBtnEnabled get() = isNameValid() && isAgeValid() && isJobTitleValid() && isGenderTypeValid()
}