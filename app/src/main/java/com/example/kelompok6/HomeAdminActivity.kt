package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity

class HomeAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_admin)

        val backImageView = findViewById<ImageView>(R.id.ic_back)
        val manageButton = findViewById<Button>(R.id.bt_manage)

        backImageView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        manageButton.setOnClickListener{
            val intent = Intent(this, ListHotelActivity::class.java)
            startActivity(intent)
        }
    }
}