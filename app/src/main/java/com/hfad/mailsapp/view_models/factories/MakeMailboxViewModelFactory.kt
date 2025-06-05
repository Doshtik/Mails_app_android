package com.hfad.mailsapp.view_models.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.view_models.MakeMailboxViewModel

class MakeMailboxViewModelFactory(private val mailboxDao: MailboxDao): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakeMailboxViewModel::class.java)) {
            return MakeMailboxViewModel(mailboxDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}