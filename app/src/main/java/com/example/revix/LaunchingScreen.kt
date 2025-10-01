package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

class LaunchingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.launching_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LaunchingScreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fade-out animation
        val fadeOut = AlphaAnimation(1f, 0f).apply {
            duration = 6000
            fillAfter = true
        }
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.LaunchingScreen).startAnimation(fadeOut)

        // Delay navigation to OnboardingScreen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingScreen::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 5000) // 2-second delay
    }
}
