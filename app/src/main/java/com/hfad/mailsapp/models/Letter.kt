package com.hfad.mailsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "letters")
class Letter {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int = 0

    @ColumnInfo(name = "")
    var IdStatus:Int = 0

    @ColumnInfo(name = "")
    var IdMilboxSender:Int = 0

    @ColumnInfo(name = "")
    var IdMilboxRecipient:Int? = null

    @ColumnInfo(name = "")
    var Theme:String? = null

    @ColumnInfo(name = "")
    var Message:String = ""

    @ColumnInfo(name = "")
    var Date:Date = Date()

    @ColumnInfo(name = "")
    var IsFavorite: Boolean = false

    @ColumnInfo(name = "")
    var IdLabel:Int? = null

    @ColumnInfo(name = "")
    var IdCopyRecipient:Int = 0

    @ColumnInfo(name = "")
    var IsRead: Boolean = false
}