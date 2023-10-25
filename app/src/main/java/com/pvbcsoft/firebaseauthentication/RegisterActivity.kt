package com.pvbcsoft.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pvbcsoft.firebaseauthentication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: ActivityRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.registerButton?.setOnClickListener {
            val email: String = binding?.email?.text.toString()
            val password: String = binding?.password?.text.toString()
            val confirmPassword: String = binding?.passwordConfirm?.text.toString()

            if (email.isNotBlank() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    createUserWithEmailAbdPassword(email, password)
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Senha incompativel",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            } else {
                Toast.makeText(
                    this@RegisterActivity,
                    "Por favor, preencha os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun createUserWithEmailAbdPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Conta criada com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this@RegisterActivity, "Authentication Failure", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}