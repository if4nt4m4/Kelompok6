package com.example.kelompok6

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kelompok6.databinding.ActivityDetailPembayaranBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class DetailPembayaranActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityDetailPembayaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference

        // Mendengarkan perubahan pada EditText jumlah kamar
        binding.etJmlhKamar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // Memeriksa apakah teks tidak kosong
                if (!s.isNullOrBlank()) {
                    // Menghitung jumlah pembayaran berdasarkan jumlah kamar
                    val hargaTipeKamar = intent.getIntExtra("hargaTipeKamar", 0)
                    val jumlahKamar = s.toString().toInt()
                    val jumlahPembayaran = hargaTipeKamar * jumlahKamar

                    // Memperbarui TextView total pembayaran
                    val tvTotalPembayaran = findViewById<TextView>(R.id.tv_total_pembayaran)
                    tvTotalPembayaran.text = jumlahPembayaran.toString()
                }
            }
        })

        binding.btnCetakPesan.setOnClickListener {
            val jumlahPembayaranStr = binding.tvJmlhpembayaran.text.toString()
            val jumlahPembayaran = if (jumlahPembayaranStr.isNotEmpty()) {
                jumlahPembayaranStr.toInt()
            } else {
                0
            }

            val kodePembayaran = generatePaymentCode()

            val pemesanan = hashMapOf(
                "namaHotel" to binding.tvNamalngkp.text.toString(),
                "alamat" to binding.tvAlamatHotel.text.toString(),
                "tipekamar" to binding.tvTipekamar.text.toString(),
                "jumlahPembayaran" to jumlahPembayaran.toString(),
                "tanggalCheckIn" to binding.tvTglpesan.text.toString(),
                "tanggalCheckOut" to binding.tvTglkeluar.text.toString(),
                "namaPemesan" to binding.etNamapemesan.text.toString(),
                "noHp" to binding.etNohpUser.text.toString(),
                "email" to binding.etEmailUser.text.toString(),
                "umur" to binding.etUmuruser.text.toString(),
                "kodePembayaran" to kodePembayaran
            )

            databaseReference.child("Pemesanan").push().setValue(pemesanan)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    val tvTotalPembayaran = findViewById<TextView>(R.id.tv_total_pembayaran)
                    tvTotalPembayaran.text = jumlahPembayaran.toString()

                    val intent = Intent(this, TicketActivity::class.java)
                    intent.putExtra("email", binding.etEmailUser.text.toString())
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
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
        binding.tvNamalngkp.text = namaHotel
        binding.tvAlamatHotel.text = alamatHotel
        binding.tvTglpesan.setText(tanggalCheckIn)
        binding.tvTglkeluar.setText(tanggalCheckOut)
        binding.tvTipekamar.text = tipeKamar
        binding.tvJmlhpembayaran.text = hargaTipeKamar.toString()
    }

    private fun generatePaymentCode(): String {
        val sdf = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        return sdf.format(Date())
    }
}
