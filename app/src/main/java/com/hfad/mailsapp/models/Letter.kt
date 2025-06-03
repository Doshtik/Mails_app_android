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

    @ColumnInfo(name = "status_id")
    var IdStatus:Int = 0

    @ColumnInfo(name = "mailbox_sender_id")
    var IdMilboxSender:Int = 0

    @ColumnInfo(name = "mailbox_recipient_id")
    var IdMilboxRecipient:Int? = null

    @ColumnInfo(name = "theme")
    var Theme:String? = null

    @ColumnInfo(name = "message")
    var Message:String = ""

    @ColumnInfo(name = "date")
    var Date:Date = Date()

    @ColumnInfo(name = "is_favorite")
    var IsFavorite: Boolean = false

    @ColumnInfo(name = "label_id")
    var IdLabel:Int? = null

    @ColumnInfo(name = "mailbox_copy_recipient_id")
    var IdCopyRecipient:Int = 0

    @ColumnInfo(name = "is_read")
    var IsRead: Boolean = false
}