package com.example.kelompok6

import android.R
import android.app.DatePickerDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kelompok6.databinding.ActivityDetailHotelBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import java.util.*

class DetailHotelActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityDetailHotelBinding
    private lateinit var selectedDateTextView: TextView // Add this line

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        database = FirebaseDatabase.getInstance().reference

        // Initialize TextView for selected date
        selectedDateTextView = binding.tvTanggalCheckIn // Update this line

        val namakota = intent.getStringExtra("namakota")

        // Menyusun nama hotel dengan format "AYO [namakota]"
        val namaHotel = "AYO $namakota"

        // Query untuk mendapatkan data hotel dengan nama yang sesuai
        val query = database.child("Hotel").orderByChild("itemName").equalTo(namaHotel)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Inside onDataChange method
                if (snapshot.exists()) {
                    // Mendapatkan data hotel dari snapshot
                    for (hotelSnapshot in snapshot.children) {
                        val hotel = hotelSnapshot.getValue(Hotel::class.java)

                        // Menampilkan data hotel di antarmuka pengguna
                        binding.tvNamaHotel.text = hotel?.itemName
                        binding.tvAlamatHotel.text = hotel?.itemAddress

                        // Menampilkan gambar jika ada
                        if (!hotel?.itemImg.isNullOrEmpty()) {
                            val decodedImage = android.util.Base64.decode(hotel?.itemImg, android.util.Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
                            binding.ivHotel.setImageBitmap(bitmap)
                        }

                        // Set up the Spinner
                        val spinner: Spinner = binding.spinnerTipeKamar
                        val tipeKamarList = listOf(hotel?.itemTK1, hotel?.itemTK2, hotel?.itemTK3)
                        val adapter = CustomSpinnerAdapter(this@DetailHotelActivity, R.layout.simple_spinner_item, tipeKamarList)
                        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                        spinner.adapter = adapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database Error: ", error.getMessage())
            }
        })

        // Set click listener for date pickers
        binding.tvTanggalCheckIn.setOnClickListener {
            showDatePickerDialog(binding.tvTanggalCheckIn)
        }

        binding.tvTanggalCheckOut.setOnClickListener {
            showDatePickerDialog(binding.tvTanggalCheckOut)
        }

        binding.btnPesanSekarang.setOnClickListener {
            // Logika untuk menangani tombol pesan sekarang
        }
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Update the TextView with the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                textView.text = selectedDate
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
