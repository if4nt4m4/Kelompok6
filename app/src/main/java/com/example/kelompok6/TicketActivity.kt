package com.example.kelompok6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TicketActivity : AppCompatActivity() {
    private lateinit var adapter: TicketAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pemesanan)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_hotelticket)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi adapter
        adapter = TicketAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Dapatkan email dari Intent
        val userEmail = intent.getStringExtra("email")

        // Panggil metode untuk menampilkan tiket berdasarkan email
        displayTicketsByEmail(userEmail)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // Navigate back to MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }

    private fun displayTicketsByEmail(email: String?) {
        val ticketList = mutableListOf<Ticket>()

        // Ubah referensi Firebase sesuai kebutuhan
        val databaseReference = FirebaseDatabase.getInstance().getReference("Pemesanan")

        // Tambahkan listener untuk mengambil data tiket berdasarkan email
        databaseReference.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children) {
                        val namaHotel = dataSnapshot.child("namaHotel").getValue(String::class.java)
                        val alamat = dataSnapshot.child("alamat").getValue(String::class.java)
                        val tipeKamar = dataSnapshot.child("tipeKamar").getValue(String::class.java)
                        val jumlahPembayaran = dataSnapshot.child("jumlahPembayaran").getValue(String::class.java)
                        val tanggalCheckIn = dataSnapshot.child("tanggalCheckIn").getValue(String::class.java)
                        val tanggalCheckOut = dataSnapshot.child("tanggalCheckOut").getValue(String::class.java)
                        val namaPemesan = dataSnapshot.child("namaPemesan").getValue(String::class.java)
                        val noHp = dataSnapshot.child("noHp").getValue(String::class.java)
                        val umur = dataSnapshot.child("umur").getValue(String::class.java)
                        val kodePembayaran = dataSnapshot.child("kodePembayaran").getValue(String::class.java)

                        // Buat objek Ticket dari data Firebase
                        val firebaseTicket = Ticket(
                            namaHotel = namaHotel.orEmpty(),
                            alamat = alamat.orEmpty(),
                            tipeKamar = tipeKamar.orEmpty(),
                            jumlahPembayaran = jumlahPembayaran.orEmpty(),
                            tanggalCheckIn = tanggalCheckIn.orEmpty(),
                            tanggalCheckOut = tanggalCheckOut.orEmpty(),
                            namaPemesan = namaPemesan.orEmpty(),
                            noHp = noHp.orEmpty(),
                            umur = umur.orEmpty(),
                            kodePembayaran = kodePembayaran.orEmpty()
                        )

                        // Tambahkan tiket ke list
                        ticketList.add(firebaseTicket)
                    }

                    // Set data tiket ke adapter
                    adapter.setData(ticketList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Database Error: ", error.message)
                }
            })
    }
}
