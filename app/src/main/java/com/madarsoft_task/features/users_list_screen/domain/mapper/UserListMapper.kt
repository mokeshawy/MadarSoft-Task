package com.madarsoft_task.features.users_list_screen.domain.mapper

import com.madarsoft_task.features.common.data.locale.entity.UserEntity
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel


fun UserEntity.toUserUiModel() = UserUiModel(
    userId = userId,
    name = name,
    age = age,
    jobTitle = jobTitle,
    genderType = genderType
)