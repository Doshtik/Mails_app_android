package com.hfad.mailsapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mail_labels")
class MailLabel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @ColumnInfo(name = "label_name")
    var LabelName: String = ""
}