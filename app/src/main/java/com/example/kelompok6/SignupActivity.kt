package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignupActivity : AppCompatActivity() {
    private fun isValidSIgnUp(namadepan: String, namablkng: String, email: String, password: String): Boolean {
        return namadepan.isNotEmpty() && namablkng.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val namadepanEditText = findViewById<EditText>(R.id.et_namadepan)
        val namablkngEditText = findViewById<EditText>(R.id.et_namablkng)
        val emailEditText = findViewById<EditText>(R.id.et_email)
        val passwordEditText = findViewById<EditText>(R.id.et_pwd)
        val btDaftarButton = findViewById<Button>(R.id.bt_daftar)

        btDaftarButton.setOnClickListener{
            val namadepan = namadepanEditText.text.toString()
            val namablkng = namablkngEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidSIgnUp(namadepan, namablkng, email, password)){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

    }
}