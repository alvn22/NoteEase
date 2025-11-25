package com.example.noteease

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi Views (Menghubungkan dengan ID di XML)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)

        // 2. Aksi saat tombol "Masuk" diklik
        btnLogin.setOnClickListener {
            performLogin()
        }

        // 3. Aksi saat teks "Lupa Password" diklik
        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // Validasi Input Kosong
        if (email.isEmpty()) {
            etEmail.error = "Email atau Nomor HP tidak boleh kosong"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Password tidak boleh kosong"
            etPassword.requestFocus()
            return
        }

        if (email == "admin" && password == "123456") {

            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            // Jika login gagal
            Toast.makeText(this, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
        }
    }
}