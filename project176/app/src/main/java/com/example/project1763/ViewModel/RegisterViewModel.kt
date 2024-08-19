package com.example.project1763.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.project1763.Helper.SharedPreferencesHelper
import com.example.project1763.Model.User

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferencesHelper = SharedPreferencesHelper(application)

    fun registerUser(user: User): Boolean {
        // Mã hóa mật khẩu trước khi lưu
        val hashedPassword = sharedPreferencesHelper.hashPassword(user.password)
        val newUser = User(user.email, hashedPassword)
        sharedPreferencesHelper.saveUser(newUser)
        return true
    }
}

class RegisterViewModelFactory(private val application: Application) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(application) as T
    }
}