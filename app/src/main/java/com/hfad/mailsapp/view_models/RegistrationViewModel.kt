package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.mailsapp.dao.RoleDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.models.User
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userDao: UserDao, private val roleDao: RoleDao): ViewModel() {
    var firstname: String = ""
    var lastname: String = ""
    var phoneNumber: String = ""
    var password: String = ""

    fun isUserExist(): Boolean {
        if (userDao.getByPhoneNumber(phoneNumber) != null)
            return true
        else
            return false
    }

    fun create() {
        viewModelScope.launch {
            val user = User()
            val role = roleDao.getByRoleName("user")
            user.IdRole = role!!.Id
            user.FirstName = firstname
            user.LastName = lastname
            user.PhoneNumber = phoneNumber
            user.Password = password
            userDao.insert(user)
        }
    }
}