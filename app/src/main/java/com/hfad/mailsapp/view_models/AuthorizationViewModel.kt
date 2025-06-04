package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import com.hfad.mailsapp.dao.UserDao

//ЗАЧЕМ СОЗДАВАТЬ ОТДЕЛЬНЫЙ VM ДЛЯ ОДНОГО ДЕЙСТВИЯ????
class AuthorizationViewModel(val userDao: UserDao): ViewModel() {
    var userId:Int = 0
    var phoneNumber: String = ""
    var password: String = ""

    fun userAvailabilityConfirm(): Boolean {
        var user = userDao.getByLoginData(phoneNumber)
        if (user != null && user.Password == password)
        {
            userId = user.Id
            return true
        }
        else
        {
            return false
        }
    }
}
