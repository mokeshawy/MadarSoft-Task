package com.madarsoft_task.features.add_new_user_screen.domain.event

import com.core.bases.base_viewmodel.ViewIntent

sealed class AddNewUserIntent : ViewIntent {

    data class NameValidationIntent(val name: String) : AddNewUserIntent()

    data class AgeValidationIntent(val age: String) : AddNewUserIntent()

    data class JobTitleValidationIntent(val jobTitle: String) : AddNewUserIntent()

    data class GenderValidationIntent(val genderType: String) : AddNewUserIntent()


}