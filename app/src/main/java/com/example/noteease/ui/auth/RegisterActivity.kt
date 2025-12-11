package com.example.noteease.ui.auth // Sesuaikan dengan nama package project Anda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteease.MainActivity
import com.example.noteease.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val inpUsername = findViewById<TextInputEditText>(R.id.inpUsername)
        val inpEmail = findViewById<TextInputEditText>(R.id.inpEmail)
        val inpPassword = findViewById<TextInputEditText>(R.id.inpPassword)
        val inpConfirmPassword = findViewById<TextInputEditText>(R.id.inpConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = inpUsername.text.toString()
            val email = inpEmail.text.toString()
            val password = inpPassword.text.toString()
            val confirmPassword = inpConfirmPassword.text.toString()
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                inpConfirmPassword.error = "Password tidak sama!"
                return@setOnClickListener
            }

            val newUser = User(username, email, password)
            UserRepository.addUser(newUser)

            Toast.makeText(this, "Registrasi berhasil, silahkan login", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}