package com.madarsoft_task.features.users_list_screen.data.repository

import com.madarsoft_task.features.common.data.locale.dao.UserDao
import com.madarsoft_task.features.users_list_screen.domain.repository.RetrieveUsersRepository
import javax.inject.Inject

class RetrieveUsersRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    RetrieveUsersRepository {

    override suspend fun retrieveAllUsers() = userDao.getAllUsers()
}