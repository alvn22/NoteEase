package com.example.noteease.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteease.MainActivity
import com.example.noteease.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    private lateinit var inpEmail: EditText
    private lateinit var inpPassword: EditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var forgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inpEmail = findViewById(R.id.inpEmail)
        inpPassword = findViewById(R.id.inpPassword)
        btnLogin = findViewById(R.id.btnLogin)
        forgotPassword = findViewById(R.id.forgotPassword)

        btnLogin.setOnClickListener {
            performLogin()
        }

        forgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        val email = inpEmail.text.toString().trim()
        val password = inpPassword.text.toString().trim()

        if (email.isEmpty()) {
            inpEmail.error = "Email atau Nomor HP tidak boleh kosong"
            inpEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            inpPassword.error = "Password tidak boleh kosong"
            inpPassword.requestFocus()
            return
        }

        if (email == "admin" && password == "12345") {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
        }
    }
}