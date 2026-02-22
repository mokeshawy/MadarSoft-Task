package com.madarsoft_task.features.add_new_user_screen.domain.model.state

import com.core.bases.base_viewmodel.ViewState
import com.core.error.AppError
import com.holouly.core.ui_component.ui_text.UiText

data class AddNewUserState(

    val name: String = "",
    val nameError: UiText? = null,
    val age: String = "",
    val ageError: UiText? = null,
    val jobTitle: String = "",
    val jobTitleError: UiText? = null,
    val genderType: String = "",
    val genderTypeError: UiText? = null,

    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val isAddedSuccess: Boolean = false

) : ViewState
