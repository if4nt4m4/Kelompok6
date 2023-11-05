package com.example.kelompok6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TicketActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_ticket_list)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_hotelticket)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Membuat adapter dan menghubungkannya dengan recyclerView
        val ticketList = createTicketList()
        val adapter = TicketAdapter(ticketList)
        recyclerView.adapter = adapter
    }
    private fun createTicketList(): MutableList<Ticket> {
        val ticketList = mutableListOf<Ticket>()

        // Contoh tiket pertama
        val ticket1 = Ticket("Hotel ABC", "2023-11-15", "Double Room", "2023-12-15")
        ticketList.add(ticket1)

        // Contoh tiket kedua
        val ticket2 = Ticket("Hotel XYZ", "2023-11-20", "Suite Room", "2023-12-20")
        ticketList.add(ticket2)

        // Anda dapat terus menambahkan tiket sesuai kebutuhan

        return ticketList
    }
}
