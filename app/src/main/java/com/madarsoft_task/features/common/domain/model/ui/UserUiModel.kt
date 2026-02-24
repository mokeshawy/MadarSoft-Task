package com.madarsoft_task.features.common.domain.model.ui

import com.madarsoft_task.features.common.data.locale.entity.UserEntity

data class UserUiModel(
    val userId : Long? = null,
    val name: String? = null,
    val age: Int? = null,
    val jobTitle: String? = null,
    val genderType: String? = null
) {
    fun toUserEntity() = UserEntity(
        name = name,
        age = age,
        jobTitle = jobTitle,
        genderType = genderType
    )
}

