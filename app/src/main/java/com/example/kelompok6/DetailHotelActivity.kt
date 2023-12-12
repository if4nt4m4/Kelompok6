package com.example.kelompok6

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kelompok6.databinding.ActivityDetailHotelBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class DetailHotelActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityDetailHotelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        database = FirebaseDatabase.getInstance().reference

        // Query untuk mendapatkan data hotel dengan itemName "ayo fahrul"
        val query = database.child("Hotel").orderByChild("itemName").equalTo("ayo fahrul")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Mendapatkan data hotel dari snapshot
                    for (hotelSnapshot in snapshot.children) {
                        val hotel = hotelSnapshot.getValue(Hotel::class.java)

                        // Menampilkan data hotel di antarmuka pengguna
                        binding.tvNamaHotel.text = hotel?.itemName
                        binding.tvAlamatHotel.text = hotel?.itemAddress
                        // ... set data lainnya sesuai kebutuhan

                        // Menampilkan gambar jika ada
                        if (!hotel?.itemImg.isNullOrEmpty()) {
                            val decodedImage = android.util.Base64.decode(hotel?.itemImg, android.util.Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
                            binding.ivHotel.setImageBitmap(bitmap)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database Error: ", error.getMessage())
            }
        })

        binding.btnPesanSekarang.setOnClickListener {
            // Logika untuk menangani tombol pesan sekarang
        }
    }
}

