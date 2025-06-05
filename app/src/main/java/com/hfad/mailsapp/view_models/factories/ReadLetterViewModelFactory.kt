package com.hfad.mailsapp.view_models.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.view_models.MakeMailboxViewModel
import com.hfad.mailsapp.view_models.ReadLetterViewModel

class ReadLetterViewModelFactory(private val letterDao: LetterDao, private val mailboxDao: MailboxDao): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadLetterViewModel::class.java)) {
            return ReadLetterViewModel(letterDao, mailboxDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}