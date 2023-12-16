package com.example.kelompok6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(private var ticketList: MutableList<Ticket>) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    fun setData(newList: List<Ticket>) {
        ticketList.clear()
        ticketList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pemesanan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = ticketList[position]

        // Setel data tiket yang sesuai pada TextView di ViewHolder
        holder.hotelNameTextView.text = ticket.namaHotel
        holder.bookingDateTextView.text = "Check-in: ${ticket.tanggalCheckIn} | Check-out: ${ticket.tanggalCheckOut}"
        holder.roomDetailsTextView.text = "Jumlah Bayar: ${ticket.jumlahPembayaran}"
        holder.dueDateTextView.text = "Kode Pembayaran: ${ticket.kodePembayaran}"
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelNameTextView: TextView = itemView.findViewById(R.id.hotelNameTextView)
        val bookingDateTextView: TextView = itemView.findViewById(R.id.bookingDateTextView)
        val roomDetailsTextView: TextView = itemView.findViewById(R.id.roomDetailsTextView)
        val dueDateTextView: TextView = itemView.findViewById(R.id.dueDateTextView)
    }
}
