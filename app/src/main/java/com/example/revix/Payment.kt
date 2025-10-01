package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paymnet) // make sure file name is correct

        // Fix: apply insets to main root layout (id=main in XML)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Back button (optional)
        val backBtn: ImageView = findViewById(R.id.btn_back)
        backBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }

        // Match Button in XML
        val payNowBtn: Button = findViewById(R.id.btnPayNow)

        // Get passed total price (if any)
        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)

        payNowBtn.setOnClickListener {
            // Navigate to success screen
            val intent = Intent(this, success::class.java)
            startActivity(intent)
            showPopup("Payment Successful! Total: $totalPrice", isSuccess = true)
            finish()
            true
        }
    }

    // Show a popup notification
    private fun showPopup(message: String, isSuccess: Boolean) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        if (isSuccess) {
            builder.setTitle("Payment Successful")
        } else {
            builder.setTitle("Error")
        }

        builder.create().show()
    }
}
