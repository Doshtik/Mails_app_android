package com.hfad.mailsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roles")
class Role {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @ColumnInfo(name = "role_name")
    var RoleName: String = ""
}