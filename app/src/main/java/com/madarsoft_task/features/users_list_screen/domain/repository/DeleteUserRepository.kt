package com.madarsoft_task.features.users_list_screen.domain.repository

interface DeleteUserRepository {
    suspend fun deleteUser(userId: Long)
}