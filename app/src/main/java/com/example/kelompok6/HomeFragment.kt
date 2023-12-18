package com.example.kelompok6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class HomeFragment : Fragment() {

    private lateinit var recyclerViewKota: RecyclerView
    private lateinit var recyclerViewWisata: RecyclerView
    private lateinit var wisataList: MutableList<Wisata>
    private lateinit var adapterWisata: WisataAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerViews and adapters
        recyclerViewKota = view.findViewById(R.id.rv_kota)
        recyclerViewWisata = view.findViewById(R.id.rv_wisata)

        val kotaList = createKotaList()
        val adapterKota = KotaAdapter(kotaList)
        recyclerViewKota.adapter = adapterKota
        adapterKota.setOnItemClickListener(object : KotaAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val kota = kotaList[position]
                val intent = Intent(requireContext(), DetailHotelActivity::class.java)
                intent.putExtra("namakota", kota.namakota)
                startActivity(intent)
            }
        })

        wisataList = createWisataList()
        adapterWisata = WisataAdapter(wisataList)
        recyclerViewWisata.adapter = adapterWisata
        adapterWisata.setOnItemClickListener(object : WisataAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click
            }
        })

        // Handle click events
        val akunImageView = view.findViewById<ImageView>(R.id.iv_akun)

        akunImageView.setOnClickListener {
            val intent = Intent(requireContext(), AkunActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createWisataList(): MutableList<Wisata> {
        val wisataList = mutableListOf<Wisata>()

        val wisata1 = Wisata(R.drawable.wisata1, "Monumen Kresek")
        wisataList.add(wisata1)

        val wisata2 = Wisata(R.drawable.wisata2, "Sarangan")
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
