package com.example.noteease

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.setupWithNavController
import com.example.noteease.ui.auth.LoginActivity
import com.example.noteease.ui.create.CreateMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.menu_bottom_nav)
        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as androidx.navigation.fragment.NavHostFragment
        val navController = navHost.navController
        bottomNav.setupWithNavController(navController)

        btnProfile.setOnClickListener {
            val intent2Profile = Intent(this, ProfileActivity::class.java)
            startActivity(intent2Profile)
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_create -> {
                    val sheet = CreateMenu()
                    sheet.show(supportFragmentManager, "CreateChooser")
                    false
                } R.id.nav_notes -> {
                    navController.navigate(R.id.nav_notes)
                    true
                } R.id.nav_todos -> {
                    navController.navigate(R.id.nav_todos)
                    true
                } else -> false
            }
        }
    }
}