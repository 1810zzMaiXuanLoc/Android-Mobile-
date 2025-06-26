package com.example.maixuanloc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEdit: EditText
    private lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEdit = findViewById(R.id.username)
        passwordEdit = findViewById(R.id.password)
    }

    fun onLogin(view: View?) {
        val username = usernameEdit.text.toString()
        val password = passwordEdit.text.toString()

        if (username == "admin" && password == "123") {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToRegister(view: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
