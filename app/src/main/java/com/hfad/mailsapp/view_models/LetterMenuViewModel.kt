package com.hfad.mailsapp.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.models.Letter
import kotlinx.coroutines.launch

class LetterMenuViewModel(private val letterDao: LetterDao, private val mailboxDao: MailboxDao) : ViewModel() {
    var mailboxSenderId: Int? = null
    private val _letters = MutableLiveData<List<Letter>>()
    val letters: LiveData<List<Letter>> get() = _letters

    fun loadInboxLetters(mailboxId: Int) {
        viewModelScope.launch {
            _letters.value = letterDao.getInboxLetters(mailboxId)
        }
    }
    fun loadSentLetters(mailboxId: Int) {
        viewModelScope.launch {
            _letters.value = letterDao.getSentLetters(mailboxId)
        }
    }
    fun loadFavoriteLetters(mailboxId: Int) {
        viewModelScope.launch {
            _letters.value = letterDao.getFavoriteLetters(mailboxId)
        }
    }
    fun loadDraftLetters(mailboxId: Int) {
        viewModelScope.launch {
            _letters.value = letterDao.getDraftLetters(mailboxId)
        }
    }
    fun loadGarbageLetters(mailboxId: Int) {
        viewModelScope.launch {
            _letters.value = letterDao.getGarbageLetters(mailboxId)
        }
    }
}