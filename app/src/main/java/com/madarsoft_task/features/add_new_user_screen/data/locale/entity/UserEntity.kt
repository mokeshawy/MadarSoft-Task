package com.madarsoft_task.features.add_new_user_screen.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madarsoft_task.features.add_new_user_screen.domain.enums.GenderType

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("age")
    val age: Int? = null,
    @ColumnInfo("job_title")
    val jobTitle: String? = null,
    @ColumnInfo("gender_type")
    val genderType: GenderType? = null
)