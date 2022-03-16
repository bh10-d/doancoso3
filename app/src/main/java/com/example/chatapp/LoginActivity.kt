package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_button = findViewById<Button>(R.id.login_button_login)
        login_button.setOnClickListener {
            handleLogin()
        }

        val toResgister = findViewById<TextView>(R.id.back_register)
        toResgister.setOnClickListener {
            Log.d("MainActivity","back to register")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun handleLogin(){
        val email = findViewById<TextView>(R.id.email_edittext_login).text.toString()
        val password = findViewById<TextView>(R.id.password_edittext_login).text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"vui long nhap email hoac mat khau",Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                Toast.makeText(this,"login successfully ",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Login fail",Toast.LENGTH_SHORT).show()
            }
    }
}