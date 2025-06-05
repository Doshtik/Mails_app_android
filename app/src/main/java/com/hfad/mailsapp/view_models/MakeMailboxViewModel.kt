package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.models.Mailbox
import kotlinx.coroutines.launch

class MakeMailboxViewModel(private val mailboxDao: MailboxDao): ViewModel() {
    var userId: Int? = null
    var mailName: String? = null

    fun create() {
        viewModelScope.launch {
            var mailbox: Mailbox = Mailbox()
            mailbox.IdUser = userId!!
            mailbox.MailName = mailName!!
            mailboxDao.insert(mailbox)
        }
    }
    fun isMailNameAreTaken(): Boolean {
        if (mailboxDao.getByMailName(mailName!!) != null)
            return false
        else
            return true
    }
}