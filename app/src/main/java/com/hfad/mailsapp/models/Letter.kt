package com.hfad.mailsapp.models

import android.content.res.Resources.Theme
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "letters")
class Letter(mailboxSenderId: Int, mailboxCopyRecipientId: Int, theme: String, message: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int = 0

    @ColumnInfo(name = "status_id")
    var IdStatus:Int = 0

    @ColumnInfo(name = "mailbox_sender_id")
    var IdMilboxSender:Int = mailboxSenderId

    @ColumnInfo(name = "mailbox_recipient_id")
    var IdMilboxRecipient:Int? = null

    @ColumnInfo(name = "theme")
    var Theme:String? = theme

    @ColumnInfo(name = "message")
    var Message:String = message

    @ColumnInfo(name = "date")
    var Date:Date = Date()

    @ColumnInfo(name = "is_favorite")
    var IsFavorite: Boolean = false

    @ColumnInfo(name = "label_id")
    var IdLabel:Int? = null

    @ColumnInfo(name = "mailbox_copy_recipient_id")
    var IdCopyRecipient:Int = mailboxCopyRecipientId

    @ColumnInfo(name = "is_read")
    var IsRead: Boolean = false
}