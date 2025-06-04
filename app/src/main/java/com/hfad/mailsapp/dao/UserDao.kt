package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)
    @Query("SELECT * FROM users WHERE id = :userId")
    fun get(userId: Int): LiveData<User>
    @Query("SELECT * FROM users WHERE phone_number = :phoneNumber")
    fun getByLoginData(phoneNumber: String): User?
}