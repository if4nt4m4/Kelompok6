package com.example.kelompok6

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TicketFragment : Fragment(R.layout.fragment_pemesanan) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ticketAdapter: TicketAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_hotelticket)
        ticketAdapter = TicketAdapter(mutableListOf())

        // Set layout manager for RecyclerView (LinearLayoutManager, GridLayoutManager, etc.)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ticketAdapter

        // Load and display ticket data
        loadTicketData()
    }

    private fun loadTicketData() {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val userEmail = user.email
            val databaseReference = FirebaseDatabase.getInstance().getReference("Pemesanan")

            // Query tickets for the current user
            databaseReference.orderByChild("email").equalTo(userEmail)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val ticketList = mutableListOf<Ticket>()

                        for (ticketSnapshot in snapshot.children) {
                            val ticket = ticketSnapshot.getValue(Ticket::class.java)
                            if (ticket != null) {
                                ticketList.add(ticket)
                            }
                        }

                        // Update the adapter with the ticket data
                        ticketAdapter.setData(ticketList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle error
                        Toast.makeText(requireContext(), "Gagal mengambil data tiket", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}