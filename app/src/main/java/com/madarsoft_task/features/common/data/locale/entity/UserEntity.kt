package com.madarsoft_task.features.common.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("age")
    val age: Int? = null,
    @ColumnInfo("job_title")
    val jobTitle: String? = null,
    @ColumnInfo("gender_type")
    val genderType: String? = null
)