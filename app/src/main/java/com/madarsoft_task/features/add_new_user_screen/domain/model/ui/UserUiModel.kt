package com.madarsoft_task.features.add_new_user_screen.domain.model.ui

import com.madarsoft_task.features.add_new_user_screen.domain.enums.UserGender

data class UserUiModel(
    val userId: String? = null,
    val name: String? = null,
    val age: Int? = null,
    val userGender: UserGender? = null
)
