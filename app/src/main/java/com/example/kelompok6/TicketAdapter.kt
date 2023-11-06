package com.example.kelompok6
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(private val ticketList: MutableList<Ticket>) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return ViewHolder(view)
    }
    fun addTicket(ticket: Ticket) {
        ticketList.add(ticket)
        notifyDataSetChanged() // Memberitahu adapter bahwa data telah berubah
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.hotelNameTextView.text = ticket.hotelName
        holder.bookingDateTextView.text = ticket.bookingDate
        holder.roomDetailsTextView.text = ticket.roomDetails
        holder.dueDateTextView.text = ticket.dueDate
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
