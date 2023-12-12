package com.example.kelompok6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kelompok6.databinding.ActivityDetailHotelBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailHotelActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityDetailHotelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.ivHotel
//        binding.tvNamaHotel
//        binding.etTanggalCheckIn
//        binding.etTanggalCheckOut
//        binding.tvTipekamar
//        binding.rgTipeKamar
//        binding.etJmlhKamar

        database = FirebaseDatabase.getInstance().reference

        binding.btnPesanSekarang.setOnClickListener {

        }
    }
}