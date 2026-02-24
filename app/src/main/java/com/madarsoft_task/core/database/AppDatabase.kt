package com.madarsoft_task.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madarsoft_task.features.common.data.locale.dao.UserDao
import com.madarsoft_task.features.common.data.locale.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}