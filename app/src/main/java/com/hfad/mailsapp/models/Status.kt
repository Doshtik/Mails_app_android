package com.hfad.mailsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statuses")
class Status {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @ColumnInfo(name = "status_name")
    var StatusName: String = ""
}