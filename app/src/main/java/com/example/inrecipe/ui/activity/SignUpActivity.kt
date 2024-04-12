package com.example.inrecipe.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.inrecipe.R
import com.example.inrecipe.data.Data
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.title = "Регистрация"
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.orange))

        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText_register)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText_register)
        val registerButton = findViewById<MaterialButton>(R.id.registerButton)

        val mAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            if (emailEditText.text != null && passwordEditText.text != null) {
                if (emailEditText.text!!.isEmpty() or passwordEditText.text!!.isEmpty()) {
                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {
                    mAuth.createUserWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Log.d("ASD", "signInWithEmail:success")

                                val user = hashMapOf(
                                    "id" to mAuth.currentUser!!.uid,
                                    "favorites" to listOf<Int>()
                                )
                                Data.database.collection("users")
                                    .document(mAuth.currentUser!!.uid)
                                    .set(user)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d("AAA", "DocumentSnapshot added")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.d("TAG", "Error adding document", e)
                                    }

                                val intent = Intent(this, AuthActivity::class.java)
                                Data.uid = mAuth.currentUser!!.uid
                                startActivity(intent)
                                finish()
                            } else {
                                Log.d("BBB", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, "Возникла ошибка (${task.exception?.message})",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}