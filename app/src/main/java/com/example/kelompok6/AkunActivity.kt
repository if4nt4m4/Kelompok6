package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class AkunActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        val btnLogout = findViewById<Button>(R.id.bt_logout)

        btnLogout.setOnClickListener {
            btnLogout()
        }
    }

    private fun btnLogout(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        startActivity(Intent(this,LoginActivity::class.java))
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        finish()
    }
}