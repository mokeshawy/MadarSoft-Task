package com.madarsoft_task.features.add_user_screen.domain.repository

import com.madarsoft_task.features.common.data.locale.entity.UserEntity

interface AddUserRepository {

    suspend fun addUser(user: UserEntity)
}