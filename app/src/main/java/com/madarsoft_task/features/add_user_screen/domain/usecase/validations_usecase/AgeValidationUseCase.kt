package com.madarsoft_task.features.add_user_screen.domain.usecase.validations_usecase

import com.core.bases.base_usecase.BaseUseCase
import com.holouly.core.ui_component.ui_text.UiText
import com.madarsoft_task.R
import com.madarsoft_task.features.add_user_screen.domain.model.state.AddUserState
import com.madarsoft_task.features.common.domain.validation_state.ValidationResult
import javax.inject.Inject

class AgeValidationUseCase @Inject constructor() : BaseUseCase<AddUserState, ValidationResult> {

    override fun execute(input: AddUserState): ValidationResult {
        if (input.age.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(resId = R.string.enter_age)
            )
        }
        return ValidationResult(isSuccessful = true, errorMessage = null)
    }
}