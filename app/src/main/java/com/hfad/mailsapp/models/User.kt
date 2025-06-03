package com.hfad.mailsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0
    @ColumnInfo(name = "id_role")
    var IdRole: Int = 0
    @ColumnInfo(name = "firstname")
    var FirstName: String = ""
    @ColumnInfo(name = "lastname")
    var LastName: String = ""
    @ColumnInfo(name = "phone_number")
    var PhoneNumber: String = ""
    @ColumnInfo(name = "password")
    var Password: String = ""
}