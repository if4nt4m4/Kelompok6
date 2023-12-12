package com.example.kelompok6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.kelompok6.databinding.ActivityDetailHotelBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailHotelActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding: ActivityDetailHotelBinding

    private lateinit var PesanSekarang: Button
    private lateinit var kamarDeluxe: RadioButton
    private lateinit var kamarSuperior: RadioButton
    private lateinit var kamarStandard: RadioButton
    private lateinit var jumlahKamar: EditText
    private lateinit var totalPembayaran: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hotel)

        PesanSekarang = findViewById(R.id.btn_pesan_sekarang)
        kamarDeluxe = findViewById(R.id.rb_deluxe)
        kamarSuperior = findViewById(R.id.rb_superior)
        kamarStandard = findViewById(R.id.rb_standard)
        jumlahKamar = findViewById(R.id.et_jmlh_kamar)

        database = FirebaseDatabase.getInstance().reference

        binding.btnPesanSekarang.setOnClickListener {

        }
    }
}