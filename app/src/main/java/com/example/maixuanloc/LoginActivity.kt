package com.example.maixuanloc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEdit = findViewById(R.id.email)
        passwordEdit = findViewById(R.id.password)
    }

    fun onLogin(view: View?) {
        val email = emailEdit.text.toString().trim()
        val pass = passwordEdit.text.toString().trim()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", "")
            val savedPass = sharedPref.getString("password", "")

            if (email == savedEmail && pass == savedPass) {
                // Lưu lại email để HomeActivity dùng
                val appPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                appPref.edit()
                    .putString("email", email)
                    .apply()

                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Sai email hoặc mật khẩu!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToRegister(view: View?) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
