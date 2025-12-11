package com.example.noteease.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteease.MainActivity
import com.example.noteease.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inpUsn = findViewById<EditText>(R.id.inpEmail)
        val inpPassword = findViewById<EditText>(R.id.inpPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = inpUsn.text.toString()
            val password = inpPassword.text.toString()

            if (UserRepository.login(username, password)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}