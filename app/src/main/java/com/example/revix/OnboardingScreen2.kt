package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OnboardingScreen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_screen2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView3 : Button = findViewById(R.id.nxtbtn2)

        textView3.setOnClickListener {
            val intent = Intent(this,OnboardingScreen3::class.java)
            startActivity(intent)
        }
        val skip2 : TextView = findViewById(R.id.textView2)

        skip2.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
        }
    }
}