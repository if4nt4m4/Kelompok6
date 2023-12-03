package com.example.kelompok6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kelompok6.databinding.ActivityDetailPembayaranBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailPembayaranActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityDetailPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembayaran)

        databaseReference = FirebaseDatabase.getInstance().reference

        binding.btnCetakPesan.setOnClickListener {
            val pemesanan = hashMapOf(
                "namaHotel" to binding.tvNmhotel.text.toString(),
                "alamat" to binding.tvAlamatHotel.text.toString(),
                "tipekamar" to binding.tvTipekamar.text.toString(),
                "jumlahPembayaran" to binding.tvJmlhpembayaran.text.toString(),
                "tanggalCheckIn" to binding.etTglpesan.text.toString(),
                "tanggalCheckOut" to binding.etTglkeluar.text.toString(),
                "namaPemesan" to binding.etNamapemesan.text.toString(),
                "noHp" to binding.etNohpUser.text.toString(),
                "email" to binding.etEmailUser.text.toString(),
                "umur" to binding.etUmuruser.text.toString()
            )
        }
    }
}