package com.madarsoft_task.features.users_list_screen.di

import com.madarsoft_task.features.users_list_screen.data.repository.RetrieveUsersRepositoryImpl
import com.madarsoft_task.features.users_list_screen.domain.repository.RetrieveUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserListModule {

    @Binds
    abstract fun bindRetrieveUsersRepository(retrieveUsersRepositoryImpl: RetrieveUsersRepositoryImpl): RetrieveUsersRepository
}