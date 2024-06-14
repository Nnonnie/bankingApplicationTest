
package com.nnech.Ndiulor.presentation.login.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bielfernandezb.samplebank.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nnech.Ndiulor.presentation.BaseActivity
import com.nnech.Ndiulor.presentation.transaction.view.activity.MainActivity

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firestore = Firebase.firestore
        auth = Firebase.auth
        binding.imgLogin.setOnClickListener {
            validateData(this)
        }

        /*binding.imgLogin.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }*/
    }

    private fun validateData(context: Context) {
        val email = binding.inputUserId.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter both email and password", Toast.LENGTH_SHORT).show()
        } else {
            loginUser(context, email, password)
        }
    }

    private fun loginUser(context: Context, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithEmail:success")
                    moveOn(context)
                } else {
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e("LoginActivity", "signInWithEmail:failure", e)
                Toast.makeText(context, "Authentication Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun moveOn(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        finish()
    }
}
