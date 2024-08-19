package com.example.project1763.Helper

import android.content.Context
import android.content.SharedPreferences
import com.example.project1763.Model.User


class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString("email", user.email)
        editor.putString("password", user.password) // Lưu mật khẩu (đã mã hóa nếu cần)
//        editor.putString("sdt",user.sdt)
//        editor.putString("diachi", user.diachi)
        editor.apply()
    }

    fun getUser(): User? {
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        val sdt = sharedPreferences.getString("sdt",null)
        val diachi = sharedPreferences.getString("diachi", null)
        return if (email != null && password != null) {
            User(email, password)
        } else {
            null
        }
    }

    fun hashPassword(password: String): String {
        // Thực hiện mã hóa mật khẩu
        return password // Thay đổi thành phương pháp mã hóa thực sự (nếu cần)
    }
}