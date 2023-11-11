package com.example.kelompok6

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val akunImageView = findViewById<ImageView>(R.id.iv_akun)

        akunImageView.setOnClickListener {
            val intent = Intent(this, AkunActivity::class.java)
            startActivity(intent)
        }

        val recyclerViewkota = findViewById<RecyclerView>(R.id.rv_kota)
        val recyclerViewWisata = findViewById<RecyclerView>(R.id.rv_wisata)

        val kotaList = createKotaList()
        val adapter1 = KotaAdapter(kotaList)
        recyclerViewkota.adapter = adapter1

        val wisataList = createWisataList()
        val adapter2 = WisataAdapter(wisataList)
        recyclerViewWisata.adapter = adapter2

    }

    private fun createWisataList(): MutableList<Wisata> {
        val wisataList = mutableListOf<Wisata>()

        val wisata1 = Wisata(R.drawable.logo, "Monumen Kresek")
        wisataList.add(wisata1)

        return wisataList
    }

    private fun createKotaList(): MutableList<Kota> {
        val kotaList = mutableListOf<Kota>()

        val kota1 = Kota(R.drawable.logo, "Madiun")
        kotaList.add(kota1)

        val kota2 = Kota(R.drawable.logo, "Magetan")
        kotaList.add(kota2)

        val kota3 = Kota(R.drawable.logo, "Ngawi")
        kotaList.add(kota3)

        val kota4 = Kota(R.drawable.logo, "Caruban")
        kotaList.add(kota4)

        return kotaList
    }
}
