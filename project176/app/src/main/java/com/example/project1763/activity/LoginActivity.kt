package com.example.project1763.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project1763.R
import com.example.project1763.ViewModel.LoginViewModel
import com.example.project1763.ViewModel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView

    // Tạo instance của ViewModel
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Kết nối các view với code
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        // Xử lý sự kiện nhấn nút "Đăng Nhập"
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Gọi hàm loginUser từ ViewModel và nhận thông báo
                val message = loginViewModel.loginUser(email, password)
                if (message == "Đăng nhập thành công") {
                    // Đăng nhập thành công, chuyển đến màn hình chính
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Để không quay lại màn hình đăng nhập khi nhấn nút "Back"
                } else {
                    // Hiển thị thông báo lỗi
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            } else {
                // Thông báo lỗi nếu chưa nhập đầy đủ email và mật khẩu
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý sự kiện nhấn vào "Đăng ký"
        tvRegister.setOnClickListener {
            // Chuyển đến màn hình đăng ký
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}