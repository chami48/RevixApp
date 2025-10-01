package com.example.revix

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Motor_oil : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_motor_oil)

        try {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) {
            Log.e("Motor_oil", "Error setting window insets: ${e.message}")
        }

        // Get username from intent and display it
        val username = intent.getStringExtra("USERNAME") ?: "User"
        Log.d("Motor_oil", "Received username: $username")

        val userTextView: TextView = findViewById(R.id.user)
        userTextView.text = username

        // Buy now button functionality
        val buyNow: Button = findViewById(R.id.buy)
        buyNow.setOnClickListener {
            Log.d("Motor_oil", "Buy now clicked, navigating to Oil_one")
            val intent = Intent(this, Oil_one::class.java)
            intent.putExtra("USERNAME", getUsername())
            startActivity(intent)
        }

        // User profile navigation
        val user: TextView = findViewById(R.id.user)
        user.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("USERNAME", getUsername())
            startActivity(intent)
        }

        // Profile image navigation - handle if view doesn't exist
        try {
            val imageView6: ImageView = findViewById(R.id.imageView6)
            imageView6.setOnClickListener {
                val intent = Intent(this, Profile::class.java)
                intent.putExtra("USERNAME", getUsername())
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.w("Motor_oil", "imageView6 not found: ${e.message}")
        }

        // Back navigation - handle if view doesn't exist
        try {
            val back: ImageView = findViewById(R.id.menuline)
            back.setOnClickListener {
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("USERNAME", getUsername())
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.w("Motor_oil", "menuline not found: ${e.message}")
        }

        // Bottom navigation setup - use correct ID from XML
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.oilBtn // Set current selected item

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBtn -> {
                    val intent = Intent(this, HomePage::class.java)
                    intent.putExtra("USERNAME", getUsername())
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.oilBtn -> {
                    // Already on oil page, do nothing
                    true
                }
                R.id.profileBtn -> {
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtra("USERNAME", getUsername())
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    // Helper function to get current username
    private fun getUsername(): String {
        val userTextView: TextView = findViewById(R.id.user)
        return userTextView.text.toString()
    }
}