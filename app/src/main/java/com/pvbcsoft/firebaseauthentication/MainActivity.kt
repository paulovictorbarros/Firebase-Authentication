package com.pvbcsoft.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pvbcsoft.firebaseauthentication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.loginButton?.setOnClickListener {
            val email: String = binding?.email?.text.toString()
            val password: String = binding?.password?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmailAndPassword(email, password)
            } else {
                Toast.makeText(
                    this@MainActivity, "Por favor, preencha os campos", Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding?.tvCreateAccount?.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@MainActivity, "sucesso ao logar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Authentication Failure", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}