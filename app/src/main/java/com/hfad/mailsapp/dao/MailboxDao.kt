package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.Mailbox

@Dao
interface MailboxDao {
    @Insert
    suspend fun insert(mailbox: Mailbox)
    @Update
    suspend fun update(mailbox: Mailbox)
    @Delete
    suspend fun delete(mailbox: Mailbox)
    @Query("SELECT * FROM mailboxes WHERE id = :mailboxId")
    fun get(mailboxId: Int): Mailbox?
    @Query("SELECT * FROM mailboxes WHERE user_id = :userId")
    fun getByUserId(userId: Int): List<Mailbox>
    @Query("SELECT * FROM mailboxes WHERE mail_name = :mailName")
    fun getByMailName(mailName: String): Mailbox?
}