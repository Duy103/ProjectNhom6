package com.example.project1763.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project1763.Model.User
import com.example.project1763.R
import com.example.project1763.ViewModel.RegisterViewModel
import com.example.project1763.ViewModel.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button

    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.etRegisterEmail)
        etPassword = findViewById(R.id.etRegisterPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // Xử lý sự kiện nhấn nút "Đăng Ký"
        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // Tạo User object
                    val user = User(email, password)

                    // Lưu thông tin người dùng vào SharedPreferences
                    registerViewModel.registerUser(user)

                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()

                    // Finish activity để quay lại màn hình đăng nhập
                    finish()
                } else {
                    Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}