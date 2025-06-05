package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.Letter

@Dao
interface LetterDao {
    @Insert
    suspend fun insert(letter: Letter)
    @Update
    suspend fun update(letter: Letter)
    @Delete
    suspend fun delete(letter: Letter)
    @Query("SELECT * FROM letters WHERE id = :letterId")
    fun get(letterId: Int): Letter?
    @Query("SELECT * FROM letters WHERE mailbox_sender_id = :userId")
    fun getBySenderId(userId: Int): List<Letter>

    @Query("SELECT * FROM letters WHERE mailbox_copy_recipient_id = :userId AND mailbox_recipient_id = :userId")
    fun getInboxLetters(userId: Int): List<Letter>
    @Query("SELECT * FROM letters WHERE mailbox_copy_recipient_id = :userId AND mailbox_sender_id = :userId")
    fun getSentLetters(userId: Int): List<Letter>
    @Query("SELECT * FROM letters WHERE mailbox_copy_recipient_id = :userId AND is_favorite = true")
    fun getFavoriteLetters(userId: Int): List<Letter>
    @Query("SELECT * FROM letters WHERE mailbox_copy_recipient_id = :userId AND status_id = (SELECT id FROM statuses WHERE status_name = 'draft')")
    fun getDraftLetters(userId: Int): List<Letter>
    @Query("SELECT * FROM letters WHERE mailbox_copy_recipient_id = :userId AND status_id = (SELECT id FROM statuses WHERE status_name = 'deleted')")
    fun getGarbageLetters(userId: Int): List<Letter>
}