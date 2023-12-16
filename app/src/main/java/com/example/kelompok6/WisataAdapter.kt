package com.example.kelompok6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WisataAdapter(private val wisataList: MutableList<Wisata> ) : RecyclerView.Adapter<WisataAdapter.ViewHolder>(){
    private var onItemClickListener: OnItemClickListener?=null
    private var filteredList: MutableList<Wisata> = mutableListOf()

    init {
        filteredList.addAll(wisataList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_wisata, parent, false)
        return ViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addWisata(wisata: Wisata) {
        wisataList.add(wisata)
        notifyDataSetChanged() // Memberitahu adapter bahwa data telah berubah
    }
    override fun onBindViewHolder(holder: WisataAdapter.ViewHolder, position: Int) {
        val kota = wisataList[position]
        holder.ivGambarWisata.setImageResource(kota.gambarwisata)
        holder.tvNamaWisata.text = kota.namawisata
        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: MutableList<Wisata>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGambarWisata: ImageView = itemView.findViewById(R.id.iv_wisata)
        val tvNamaWisata: TextView = itemView.findViewById(R.id.tv_wisata)

        init {
            itemView.setOnClickListener{
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}