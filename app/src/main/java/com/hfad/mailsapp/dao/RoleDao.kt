package com.hfad.mailsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hfad.mailsapp.models.Role

@Dao
interface RoleDao {
    @Insert
    suspend fun insert(role: Role)
    @Update
    suspend fun update(role: Role)
    @Delete
    suspend fun delete(role: Role)
    @Query("SELECT * FROM roles WHERE id = :roleId")
    fun get(roleId: Int): LiveData<Role>
    @Query("SELECT * FROM roles WHERE role_name = :roleName")
    fun getByRoleName(roleName: String)
}