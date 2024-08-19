package com.example.project1763.ViewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.example.project1763.Helper.SharedPreferencesHelper


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferencesHelper = SharedPreferencesHelper(application)

    fun loginUser(email: String, password: String): String {
        val storedUser = sharedPreferencesHelper.getUser()
        return if (storedUser == null) {
            "Tài khoản chưa tồn tại"
        } else if (storedUser.email == email && sharedPreferencesHelper.hashPassword(password) == storedUser.password) {
            "Đăng nhập thành công"
        } else {
            "Email hoặc mật khẩu không đúng"
        }
    }
}

class LoginViewModelFactory(private val application: Application) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(application) as T
    }
}