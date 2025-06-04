package com.hfad.mailsapp.view_models

import androidx.lifecycle.ViewModel
import com.hfad.mailsapp.dao.UserDao

class RegistrationViewModel(userDao: UserDao): ViewModel() {
    val userId: Int = 0;
    var firstname: String = ""
    var lastname: String = ""
    var phoneNumber: String = ""
    var password: String = ""

}