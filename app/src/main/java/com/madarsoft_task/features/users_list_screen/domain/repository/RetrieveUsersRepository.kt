package com.madarsoft_task.features.users_list_screen.domain.repository

import com.madarsoft_task.features.common.data.locale.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface RetrieveUsersRepository {
    suspend fun retrieveAllUsers() : Flow<List<UserEntity>>
}