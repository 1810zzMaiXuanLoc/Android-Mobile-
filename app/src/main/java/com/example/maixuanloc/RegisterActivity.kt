package com.example.maixuanloc

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEdit: EditText
    private lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameEdit = findViewById(R.id.username)
        passwordEdit = findViewById(R.id.password)
    }

    fun onRegister(view: View?) {
        val user = usernameEdit.text.toString()
        val pass = passwordEdit.text.toString()

        if (user.isNotEmpty() && pass.isNotEmpty()) {
            Toast.makeText(this, "Đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_SHORT)
                .show()
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
