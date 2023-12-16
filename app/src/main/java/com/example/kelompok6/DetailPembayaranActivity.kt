package com.example.kelompok6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kelompok6.databinding.ActivityDetailPembayaranBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailPembayaranActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityDetailPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference

        binding.btnCetakPesan.setOnClickListener {
            val pemesanan = hashMapOf(
                "namaHotel" to binding.tvNamalngkp.text.toString(),
                "alamat" to binding.tvAlamatHotel.text.toString(),
                "tipekamar" to binding.tvTipekamar.text.toString(),
                "jumlahPembayaran" to binding.tvJmlhpembayaran.text.toString(),
                "tanggalCheckIn" to binding.tvTglpesan.text.toString(),
                "tanggalCheckOut" to binding.tvTglkeluar.text.toString(),
                "namaPemesan" to binding.etNamapemesan.text.toString(),
                "noHp" to binding.etNohpUser.text.toString(),
                "email" to binding.etEmailUser.text.toString(),
                "umur" to binding.etUmuruser.text.toString()
            )

            databaseReference.child("Pemesanan").push().setValue(pemesanan).addOnSuccessListener {
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

        // Ambil data dari Intent
        val namaHotel = intent.getStringExtra("NamaHotel")
        val alamatHotel = intent.getStringExtra("alamatHotel")
        val tanggalCheckIn = intent.getStringExtra("tanggalCheckIn")
        val tanggalCheckOut = intent.getStringExtra("tanggalCheckOut")
        val tipeKamar = intent.getStringExtra("tipeKamar")
        val hargaTipeKamar = intent.getIntExtra("hargaTipeKamar", 0)

        // Tampilkan data di TextView atau EditText sesuai kebutuhan
        binding.tvNamalngkp.setText(namaHotel)
        binding.tvAlamatHotel.setText(alamatHotel)
        binding.tvTglpesan.setText(tanggalCheckIn)
        binding.tvTglkeluar.setText(tanggalCheckOut)
        binding.tvTipekamar.text = tipeKamar
        binding.tvJmlhpembayaran.text = hargaTipeKamar.toString()

    }
}