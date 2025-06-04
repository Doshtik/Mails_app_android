package com.hfad.mailsapp.view_models.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.RoleDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.view_models.RegistrationViewModel

class RegistrationViewModelFactory(private val userDao: UserDao, private val roleDao: RoleDao) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(userDao, roleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}