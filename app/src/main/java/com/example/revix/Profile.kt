package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = intent.getStringExtra("USERNAME") ?: "User" // Default to "User" if null
        val userTextView: TextView = findViewById(R.id.profile_name)
        userTextView.text = username // Display username in the TextView

        val logout : TextView = findViewById(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.profileBtn

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBtn -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.oilBtn -> {
                    val intent = Intent(this, Motor_oil::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profileBtn -> {
                    // Already on profile page, do nothing
                    true
                }
                else -> false
            }
        }
    }
}

