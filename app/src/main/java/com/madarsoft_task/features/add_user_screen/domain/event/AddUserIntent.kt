package com.madarsoft_task.features.add_user_screen.domain.event

import com.core.bases.base_viewmodel.ViewIntent
import com.madarsoft_task.features.add_user_screen.domain.model.ui.UserUiModel

sealed class AddUserIntent : ViewIntent {

    data class NameValidationIntent(val name: String) : com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent()

    data class AgeValidationIntent(val age: String) : com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent()

    data class JobTitleValidationIntent(val jobTitle: String) : com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent()

    data class GenderValidationIntent(val genderType: String) : com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent()


    data class AddUserIntent(val userUiModel: UserUiModel): com.madarsoft_task.features.add_user_screen.domain.event.AddUserIntent()

}