package com.hfad.mailsapp.view_models.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.view_models.RegistrationViewModel
import com.hfad.mailsapp.view_models.SelectorMailboxesViewModel

class SelectorMailboxesViewModelFactory(private val userDao: UserDao, private val mailboxDao:MailboxDao): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectorMailboxesViewModel::class.java)) {
            return SelectorMailboxesViewModel(userDao, mailboxDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}