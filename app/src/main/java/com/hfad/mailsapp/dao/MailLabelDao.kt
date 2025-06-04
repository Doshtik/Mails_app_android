package com.hfad.mailsapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.MailLabel

@Dao
interface MailLabelDao {
    @Insert
    suspend fun insert(mailLabel: MailLabel)
    @Update
    suspend fun update(mailLabel: MailLabel)
    @Delete
    suspend fun delete(mailLabel: MailLabel)
    @Query("SELECT * FROM mail_labels WHERE id = :mailLabelId")
    fun get(mailLabelId: Int): MailLabel?
    @Query("SELECT * FROM mail_labels WHERE label_name = :labelName")
    fun getByLabelName(labelName: String): MailLabel?
}