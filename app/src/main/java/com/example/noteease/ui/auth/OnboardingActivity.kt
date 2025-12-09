package com.example.noteease

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteease.ui.auth.LoginActivity
import com.example.noteease.ui.auth.RegisterActivity
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)

        btnRegister.setOnClickListener {
            val intent2Register = Intent(this, RegisterActivity::class.java)
            startActivity(intent2Register)
        }
        btnLogin.setOnClickListener {
            val intent2Login = Intent(this, LoginActivity::class.java)
            startActivity(intent2Login)
        }
    }
}