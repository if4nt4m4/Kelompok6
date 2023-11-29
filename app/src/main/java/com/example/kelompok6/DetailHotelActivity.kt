package com.example.kelompok6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kelompok6.databinding.ActivityDetailHotelBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailHotelActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding: ActivityDetailHotelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hotel)

        database = FirebaseDatabase.getInstance().reference

        binding.btnPesanSekarang.setOnClickListener {
            tambahData()
        }
    }
}