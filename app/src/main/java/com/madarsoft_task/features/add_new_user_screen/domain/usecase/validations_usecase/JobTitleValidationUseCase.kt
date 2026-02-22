package com.madarsoft_task.features.add_new_user_screen.domain.usecase.validations_usecase

import com.core.bases.base_usecase.BaseUseCase
import com.holouly.core.ui_component.ui_text.UiText
import com.madarsoft_task.R
import com.madarsoft_task.features.add_new_user_screen.domain.model.state.AddNewUserState
import com.madarsoft_task.features.common.domain.validation_state.ValidationResult
import javax.inject.Inject

class JobTitleValidationUseCase @Inject constructor() : BaseUseCase<AddNewUserState, ValidationResult> {

    override fun execute(input: AddNewUserState): ValidationResult {
        if (input.jobTitle.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(resId = R.string.enter_job_title)
            )
        }
        return ValidationResult(isSuccessful = true, errorMessage = null)
    }
}