package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao

class LetterMenuViewModel(private val letterDao: LetterDao, private val mailboxDao: MailboxDao) : ViewModel() {
    var mailboxSenderId: Int? = null

}