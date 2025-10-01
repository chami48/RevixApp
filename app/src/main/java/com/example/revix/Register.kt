package com.example.revix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Setting edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find all the views by their correct IDs
        val signupButton: Button = findViewById(R.id.signupbtn1)
        val nameEditText: EditText = findViewById(R.id.cnamehint)
        val emailEditText: EditText = findViewById(R.id.emailhint)
        val passwordEditText: EditText = findViewById(R.id.emailhint3)
        val confirmPasswordEditText: EditText = findViewById(R.id.repasswordhint)
        val phoneEditText: EditText = findViewById(R.id.emailhint2)
        val signIn: TextView = findViewById(R.id.signintext2)

        signIn.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }
        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val phone = phoneEditText.text.toString()


            // Validate the fields
            if (name.isEmpty()) {
                showPopup("Name cannot be empty")
                return@setOnClickListener
            }

            if (!email.contains("@gmail.com")) {
                showPopup("Please use a valid Gmail address")
                return@setOnClickListener
            }
            if (phone.length != 10 || !phone.startsWith("0") || !phone.all { it.isDigit() }) {
                showPopup("Phone number must start with '0' and be 10 digits long")
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                showPopup("Password must be at least 8 characters long")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showPopup("Passwords do not match")
                return@setOnClickListener
            }
            // If all fields are valid, proceed to the Login page
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

    }

    // Function to show a beautiful popup notification
    private fun showPopup(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Validation Error")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
        builder.create().show()
    }
}
