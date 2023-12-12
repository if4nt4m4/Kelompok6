package com.example.kelompok6

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView


class KotaAdapter (private val kotalist: MutableList<Kota>): RecyclerView.Adapter<KotaAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_kota, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addKota(kota: Kota){
        kotalist.add(kota)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: KotaAdapter.ViewHolder, position: Int) {
        val kota = kotalist[position]
        holder.ivGambarKota.setImageResource(kota.gambarkota)
        holder.tvNamaKota.text = kota.namakota
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return kotalist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGambarKota: ImageView = itemView.findViewById(R.id.iv_kota)
        val tvNamaKota: TextView = itemView.findViewById(R.id.tv_kota)

        init {
            itemView.setOnClickListener {
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