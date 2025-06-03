package com.hfad.mailsapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachments")
class Attachment(idLetter: Int, fileName: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @ColumnInfo(name = "id_letter")
    var IdLetter: Int = idLetter

    @ColumnInfo(name = "file_name")
    var FileName: String = fileName
}