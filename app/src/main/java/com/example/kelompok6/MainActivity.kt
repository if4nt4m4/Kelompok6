package com.example.kelompok6

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val akunImageView = findViewById<ImageView>(R.id.iv_akun)
        val tv_cari = findViewById<TextView>(R.id.tv_cari)
        val textView = findViewById<TextView>(R.id.textView)
        val tv_bantuan = findViewById<TextView>(R.id.tv_bantuan)

        akunImageView.setOnClickListener {
            val intent = Intent(this, AkunActivity::class.java)
            startActivity(intent)
        }
        tv_cari.setOnClickListener{
            replaceFragment(CariFragment())
        }
        textView.setOnClickListener {
            replaceFragment(PemesananFragment())
        }
        tv_bantuan.setOnClickListener{
            replaceFragment(BantuanFragment())
        }

        val recyclerViewkota = findViewById<RecyclerView>(R.id.rv_kota)
        val recyclerViewWisata = findViewById<RecyclerView>(R.id.rv_wisata)

        val kotaList = createKotaList()
        val adapter1 = KotaAdapter(kotaList)
        recyclerViewkota.adapter = adapter1
        adapter1.setOnItemClickListener(object : KotaAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailHotelActivity::class.java)
                intent.putExtra("namakota", createKotaList()[position].namakota)
                startActivity(intent)
            }
        })

        val wisataList = createWisataList()
        val adapter2 = WisataAdapter(wisataList)
        recyclerViewWisata.adapter = adapter2
        adapter2.setOnItemClickListener(object : WisataAdapter.OnItemClickListener{
            override fun onItemClick(position: Int){
                val intent = Intent(this@MainActivity, DetailHotelActivity::class.java)
                intent.putExtra("namawisata", createWisataList()[position].namawisata)
                startActivity(intent)
            }
        })
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun createWisataList(): MutableList<Wisata> {
        val wisataList = mutableListOf<Wisata>()

        val wisata1 = Wisata(R.drawable.wisata1, "Monumen Kresek")
        wisataList.add(wisata1)

        val wisata2 = Wisata(R.drawable.wisata2,"Sarangan")
        wisataList.add(wisata2)

        val wisata3 = Wisata(R.drawable.wisata3, "Aloon-Aloon Ngawi")
        wisataList.add(wisata3)

        val wisata4 = Wisata(R.drawable.wisata4, "Aloon-Aloon Caruban")
        wisataList.add(wisata4)

        return wisataList
    }

    private fun createKotaList(): MutableList<Kota> {
        val kotaList = mutableListOf<Kota>()

        val kota1 = Kota(R.drawable.ayomadiun, "Madiun")
        kotaList.add(kota1)

        val kota2 = Kota(R.drawable.ayomagetan, "Magetan")
        kotaList.add(kota2)

        val kota3 = Kota(R.drawable.ayongawi, "Ngawi")
        kotaList.add(kota3)

        val kota4 = Kota(R.drawable.ayocaruban, "Caruban")
        kotaList.add(kota4)

        return kotaList
    }
}
