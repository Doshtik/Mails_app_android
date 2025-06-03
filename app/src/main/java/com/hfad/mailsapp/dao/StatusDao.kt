package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.Status

@Dao
interface StatusDao {
    @Insert
    suspend fun insert(status: Status)
    @Update
    suspend fun update(status: Status)
    @Delete
    suspend fun  delete(status: Status)
    @Query("SELECT * FROM statuses WHERE id = :statusId")
    fun get(statusId: Int): LiveData<Status>
    @Query("SELECT * FROM statuses WHERE status_name= :statusName")
    fun getByStatusName(statusName: String)
}