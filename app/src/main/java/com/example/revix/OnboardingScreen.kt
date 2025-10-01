package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OnboardingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Oscreen1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnnxt :Button = findViewById(R.id.nxtbtn)

        btnnxt.setOnClickListener {
            val intent = Intent(this,OnboardingScreen2::class.java)
            startActivity(intent)
        }

        val skip :TextView = findViewById(R.id.skip)

        skip.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
        }

    }
}