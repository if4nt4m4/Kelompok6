package com.example.kelompok6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TicketActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pemesanan)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_hotelticket)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val ticketList = createTicketList()
        val adapter = TicketAdapter(ticketList)
        recyclerView.adapter = adapter
    }
    private fun createTicketList(): MutableList<Ticket> {
        val ticketList = mutableListOf<Ticket>()

        // Replace this section with Firebase code to fetch data
        // For example, using Firebase Realtime Database
        val databaseReference = FirebaseDatabase.getInstance().getReference("your_firebase_node")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val firebaseTicket = dataSnapshot.getValue(Ticket::class.java)
                    firebaseTicket?.let { ticketList.add(it) }
                }
                // Notify the adapter after fetching the data
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
            }
        })

        return ticketList
    }

}
