package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao

class ReadLetterViewModel(private val letterDao: LetterDao, private val mailboxDao: MailboxDao): ViewModel() {
    var letterId: Int? = null
    var recipient: String? = null
    var theme: String? = null
    var message: String? = null

    fun getRecipientName(): String
    {
        var recId: Int = letterDao.get(letterId!!)!!.IdMilboxRecipient!!
        var recMailbox: String = mailboxDao.get(recId)!!.MailName
        return recMailbox
    }
}