package com.hfad.mailsapp.view_models.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.view_models.AuthorizationViewModel

class AuthorizationViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorizationViewModel::class.java)) {
            return AuthorizationViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}