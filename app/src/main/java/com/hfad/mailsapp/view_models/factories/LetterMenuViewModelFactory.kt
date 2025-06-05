package com.hfad.mailsapp.view_models.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.view_models.LetterMenuViewModel
import com.hfad.mailsapp.view_models.MakeMailboxViewModel

class LetterMenuViewModelFactory(private val letterDao: LetterDao, private val mailboxDao: MailboxDao) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LetterMenuViewModel::class.java)) {
            return LetterMenuViewModel(letterDao, mailboxDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}