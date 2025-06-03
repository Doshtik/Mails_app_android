package com.hfad.mailsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mailboxes")
class Mailbox {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @ColumnInfo(name = "user_id")
    var IdUser: Int = 0

    @ColumnInfo(name = "mail_name")
    var MailName: String = ""
}