package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Masuk = findViewById<Button>(R.id.bt_masuk)
        val Lewati = findViewById<TextView>(R.id.tv_lewati)
        val Daftar = findViewById<TextView>(R.id.tv_daftar)
        val emailEditText = findViewById<EditText>(R.id.et_email)
        val passwordEditText = findViewById<EditText>(R.id.et_pwd)

        Masuk.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email == "admin" && password == "admin") {
                val intent = Intent(this, HomeAdminActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        Lewati.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        })
        //mengimplementasikan lambda
        Daftar.setOnClickListener {
            val intent = Intent (this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}