package com.example.kelompok6

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*


class CariFragment : Fragment() {

    private lateinit var adapter: WisataAdapter
    private lateinit var wisataList: MutableList<Wisata>
    private lateinit var recyclerViewWisata: RecyclerView
    private lateinit var cariEditText: EditText
    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("wisata")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cari, container, false)

        recyclerViewWisata = view.findViewById(R.id.rv_wisata)
        cariEditText = view.findViewById(R.id.et_cari)

        wisataList = mutableListOf()
        adapter = WisataAdapter(wisataList)

        recyclerViewWisata.layoutManager = LinearLayoutManager(activity)
        recyclerViewWisata.adapter = adapter

        setupSearch()

        fetchDataFromFirebase()

        return view
    }

    private fun setupSearch() {
        cariEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = mutableListOf<Wisata>()

        if (wisataList.isNotEmpty()) {
            for (item in wisataList) {
                if (item.namawisata.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item)
                }
            }

            adapter.filterList(filteredList)
        }
    }
    private fun fetchDataFromFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                wisataList.clear()

                for (postSnapshot in dataSnapshot.children) {
                    val wisata = postSnapshot.getValue(Wisata::class.java)
                    wisata?.let {
                        wisataList.add(it)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })
    }
}
