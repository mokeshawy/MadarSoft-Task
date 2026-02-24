package com.madarsoft_task.features.users_list_screen.data.repository

import com.madarsoft_task.features.common.data.locale.dao.UserDao
import com.madarsoft_task.features.users_list_screen.domain.repository.RetrieveUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RetrieveUsersRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    RetrieveUsersRepository {

    override suspend fun retrieveAllUsers() = userDao.getAllUsers().flowOn(context = Dispatchers.IO)
}