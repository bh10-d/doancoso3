package com.example.chatapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val register_button = findViewById<Button>(R.id.register_button_register);
        register_button.setOnClickListener {
            perFormRegister()
        }
        val toLogin = findViewById<TextView>(R.id.already_have_account_text_view);
        toLogin.setOnClickListener {
            Log.d("MainActivity","Try to show login activity")

            //launch login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val selectphoto_button_register = findViewById<Button>(R.id.selectphoto_button_register)
        selectphoto_button_register.setOnClickListener {
            Log.d("MainAcivity", "try to show photo selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getResult.launch(intent)
        }
    }

    //handle
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("input")
            }
        }


    private fun perFormRegister(){
        val email = findViewById<TextView>(R.id.email_edittext_register).text.toString();
        val password = findViewById<TextView>(R.id.password_edittext_register).text.toString();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,"Please enter text in email/password",Toast.LENGTH_SHORT).show()

            return
        }
        Log.d("MainActivity","Email is: "+email);
        Log.d("MainActivity","Password is: $password");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("Main","Successfully created user with uid: ${it.result.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("Main","Failer to create user: ${it.message}")
            }
    }
}