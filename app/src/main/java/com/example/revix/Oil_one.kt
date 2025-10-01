package com.example.revix

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Oil_one : AppCompatActivity() {

    private var quantity: Int = 1  // Default quantity
    private val pricePerItem: Double = 12570.0 // Price of a single item

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_oil_one)

        // Remove the main view reference if it doesn't exist in XML
        try {
            ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) {
            Log.e("Oil_one", "Error setting window insets: ${e.message}")
        }

        // Get username from intent
        val username = intent.getStringExtra("USERNAME") ?: "User"

        // Log for debugging
        Log.d("Oil_one", "Received username: $username")

        // Initialize views with error handling
        try {
            // Try to find user TextView, but don't crash if it doesn't exist
            val userTextView: TextView? = try { findViewById(R.id.user) } catch (e: Exception) { null }
            userTextView?.text = username

            val incrementBtn: ImageView = findViewById(R.id.ic_add)
            val decrementBtn: ImageView = findViewById(R.id.ic_remove)
            val quantityText: TextView = findViewById(R.id.quantity_text)
            val priceText: TextView = findViewById(R.id.price)
            val addToCartButton: androidx.cardview.widget.CardView = findViewById(R.id.add_to_cart_button)
            val backIcon: ImageView = findViewById(R.id.back_icon)

            // Initialize quantity and price display
            updateQuantityAndPrice(quantityText, priceText)

            // Increment Button Click
            incrementBtn.setOnClickListener {
                quantity++
                updateQuantityAndPrice(quantityText, priceText)
                Log.d("Oil_one", "Quantity increased to: $quantity")
            }

            // Decrement Button Click
            decrementBtn.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    updateQuantityAndPrice(quantityText, priceText)
                    Log.d("Oil_one", "Quantity decreased to: $quantity")
                }
            }


            addToCartButton.setOnClickListener {
                Log.d("Oil_one", "Add to cart clicked, navigating to Payment")
                try {
                    val intent = Intent(this, Payment::class.java) // Fixed class name
                    intent.putExtra("USERNAME", username)
                    intent.putExtra("totalPrice", pricePerItem * quantity)
                    intent.putExtra("quantity", quantity)
                    intent.putExtra("itemName", "Mobil Engine Oil")
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e("Oil_one", "Error navigating to Payment: ${e.message}")

                }
            }


            backIcon.setOnClickListener {
                Log.d("Oil_one", "Back button clicked, navigating to Motor_oil")
                val intent = Intent(this, Motor_oil::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            }


            val userProfile: TextView? = try { findViewById(R.id.user) } catch (e: Exception) { null }
            val profileImage: ImageView? = try { findViewById(R.id.imageView6) } catch (e: Exception) { null }

            userProfile?.setOnClickListener {
                val intent = Intent(this, Profile::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }

            profileImage?.setOnClickListener {
                val intent = Intent(this, Profile::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }


            val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
            bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeBtn -> {
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.oilBtn -> {
                        val intent = Intent(this, Motor_oil::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.profileBtn -> {
                        val intent = Intent(this, Profile::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

        } catch (e: Exception) {
            Log.e("Oil_one", "Error initializing views: ${e.message}")
            e.printStackTrace()
        }
    }

    // Function to update quantity and price display
    private fun updateQuantityAndPrice(quantityText: TextView, priceText: TextView) {
        quantityText.text = quantity.toString()
        val totalPrice = pricePerItem * quantity
        priceText.text = "Rs ${"%.2f".format(totalPrice)}"
    }
}