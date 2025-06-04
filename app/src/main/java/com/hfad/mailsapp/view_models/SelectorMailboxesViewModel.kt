package com.hfad.mailsapp.view_models

import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.models.Mailbox

class SelectorMailboxesViewModel(private val userDao: UserDao, private val mailboxDao: MailboxDao): ViewModel() {
    var userId: Int = 0
    var selectedCard: CardView? = null

    fun getByUserId(): List<Mailbox>? {
        return mailboxDao.getByUserId(userId)
    }

    /*fun createCard(mailbox: Mailbox): CardView {
        return CardView()
    }*/

}