package com.example.maixuanloc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEdit = findViewById(R.id.email)
        passwordEdit = findViewById(R.id.password)
    }

    fun onRegister(view: View?) {
        val email = emailEdit.text.toString().trim()
        val pass = passwordEdit.text.toString().trim()

        if (email.isNotEmpty() && pass.isNotEmpty()) {

            // Check email định dạng
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show()
                return
            }

            // Check trùng email
            val sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", "")
            if (email == savedEmail) {
                Toast.makeText(this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show()
                return
            }

            // Check độ dài password
            if (pass.length < 6) {
                Toast.makeText(this, "Mật khẩu phải ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show()
                return
            }

            // Lưu SharedPreferences
            val editor = sharedPref.edit()
            editor.putString("email", email)
            editor.putString("password", pass)
            editor.apply()

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
        }
    }


    fun onGoToLogin(view: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
