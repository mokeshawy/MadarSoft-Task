package com.madarsoft_task.features.add_user_screen.domain.di

import com.madarsoft_task.features.add_user_screen.data.repository.AddUserRepositoryImpl
import com.madarsoft_task.features.add_user_screen.domain.repository.AddUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddUserModule {

    @Binds
    abstract fun bindAddUserRepository(addUserRepositoryImpl: AddUserRepositoryImpl): AddUserRepository
}