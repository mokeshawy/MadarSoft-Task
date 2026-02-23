package com.madarsoft_task.features.add_user_screen.data.repository

import com.madarsoft_task.features.common.data.locale.dao.UserDao
import com.madarsoft_task.features.common.data.locale.entity.UserEntity
import com.madarsoft_task.features.add_user_screen.domain.repository.AddUserRepository
import javax.inject.Inject

class AddUserRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    AddUserRepository {

    override suspend fun addUser(user: UserEntity) = userDao.insertUser(user)

}