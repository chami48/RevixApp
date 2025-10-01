package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OnboardingScreen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_screen3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nxtbtn3 : Button = findViewById(R.id.nxtbtn3)

        nxtbtn3.setOnClickListener {
            val intent = Intent(this,OnboardingScreen4::class.java)
            startActivity(intent)
        }

        val skip3 : TextView = findViewById(R.id.skip3)

        skip3.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
        }


    }
}