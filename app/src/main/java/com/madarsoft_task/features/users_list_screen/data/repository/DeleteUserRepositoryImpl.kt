package com.madarsoft_task.features.users_list_screen.data.repository

import com.madarsoft_task.features.common.data.locale.dao.UserDao
import com.madarsoft_task.features.users_list_screen.domain.repository.DeleteUserRepository
import javax.inject.Inject

class DeleteUserRepositoryImpl @Inject constructor(private val userDao: UserDao) : DeleteUserRepository {

    override suspend fun deleteUser(userId: Long) = userDao.deleteUserById(userId = userId)
}