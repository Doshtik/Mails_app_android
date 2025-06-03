package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.Attachment

@Dao
interface AttachmentDao {
    @Insert
    suspend fun insert(attachment: Attachment)

    @Update
    suspend fun update(attachment: Attachment)

    @Delete
    suspend fun delete(attachment: Attachment)

    @Query("SELECT * FROM attachments WHERE id = :attachId")
    fun get(attachId: Int): LiveData<Attachment>

    @Query("SELECT * FROM attachments WHERE id_letter = :letterId")
    fun getByLetterId(letterId: Int): LiveData<Attachment>
}