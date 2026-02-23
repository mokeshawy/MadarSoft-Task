package com.madarsoft_task.features.common.domain.validation_state

import com.holouly.core.ui_component.ui_text.UiText

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: UiText? = null
)