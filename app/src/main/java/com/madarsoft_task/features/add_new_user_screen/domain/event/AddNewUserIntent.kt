package com.madarsoft_task.features.add_new_user_screen.domain.event

import com.core.bases.base_viewmodel.ViewIntent
import com.madarsoft_task.features.add_new_user_screen.domain.enums.UserGender

sealed class AddNewUserIntent : ViewIntent {

    data class NameValidationIntent(val name: String) : AddNewUserIntent()

    data class AgeValidationIntent(val age: Int) : AddNewUserIntent()

    data class JobTitleValidationIntent(val jobTitle: String) : AddNewUserIntent()

    data class GenderValidationIntent(val userGender: UserGender) : AddNewUserIntent()


}