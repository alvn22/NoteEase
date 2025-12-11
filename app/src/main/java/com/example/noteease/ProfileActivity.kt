package com.example.noteease

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteease.ui.auth.UserRepository
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val tvUsn = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        val user = UserRepository.getUser()

        if (user != null) {
            tvUsn.text = user.username
            tvEmail.text = user.email
        }

        btnBack.setOnClickListener {
            val intent2Main = Intent(this, MainActivity::class.java)
            startActivity(intent2Main)
        }

        btnLogout.setOnClickListener {
            UserRepository.logout()
            val intentLogout = Intent(this, OnboardingActivity::class.java)
            startActivity(intentLogout)
        }
    }
}