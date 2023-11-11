package com.example.kelompok6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WisataAdapter(private val wisataList: MutableList<Wisata> ) : RecyclerView.Adapter<WisataAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_kota, parent, false)
        return ViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addTicket(wisata: Wisata) {
        wisataList.add(wisata)
        notifyDataSetChanged() // Memberitahu adapter bahwa data telah berubah
    }
    override fun onBindViewHolder(holder: WisataAdapter.ViewHolder, position: Int) {
        val kota = wisataList[position]
        holder.ivGambarWisata.setImageResource(kota.gambarwisata)
        holder.tvNamaWisata.text = kota.namawisata
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGambarWisata: ImageView = itemView.findViewById(R.id.iv_wisata)
        val tvNamaWisata: TextView = itemView.findViewById(R.id.tv_wisata)
    }
}