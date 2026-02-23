package com.madarsoft_task.core.database.di

import android.content.Context
import androidx.room.Room
import com.madarsoft_task.core.database.AppDatabase
import com.madarsoft_task.features.common.data.locale.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "madar-soft-db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

}