package com.example.kelompok6

import android.annotation.SuppressLint
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

        val ticketList = createTicketList()
        adapter = TicketAdapter(ticketList)
        recyclerView.adapter = adapter
    }

    private fun createTicketList(): MutableList<Ticket> {
        val ticketList = mutableListOf<Ticket>()

        val databaseReference = FirebaseDatabase.getInstance().getReference("Hotel")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val firebaseTicket = dataSnapshot.getValue(Ticket::class.java)
                    firebaseTicket?.let { ticketList.add(it) }
                }
                // Notify the adapter after fetching the data
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database Error: ", error.getMessage())
            }
        })

        return ticketList
    }
}
