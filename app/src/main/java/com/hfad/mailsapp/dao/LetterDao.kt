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

    @Query("SELECT * FROM letters WHERE mailbox_sender_id = :mailboxSenderId")
    fun getBySenderId(mailboxSenderId: Int): Letter?
}