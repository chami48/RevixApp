package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_new)


        val oilStore: TextView = findViewById(R.id.oil_store)
        oilStore.setOnClickListener {
            val intent = Intent(this, Motor_oil::class.java)
            startActivity(intent)
        }


        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.homeBtn 


        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBtn -> {
                    // Already on HomePage → do nothing
                    true
                }
                R.id.oilBtn -> {
                    val intent = Intent(this, Motor_oil::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profileBtn -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}