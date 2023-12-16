package com.example.kelompok6

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var wisataList: MutableList<Wisata>
    private lateinit var adapter: WisataAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        databaseReference = FirebaseDatabase.getInstance().getReference("wisata")

        val akunImageView = findViewById<ImageView>(R.id.iv_akun)
        val tv_cari = findViewById<TextView>(R.id.tv_cari)
        val textView = findViewById<TextView>(R.id.textView)
        val tv_bantuan = findViewById<TextView>(R.id.tv_bantuan)
//        val iv_kota = findViewById<CardView>(R.id.cv_kota)

        akunImageView.setOnClickListener {
            val intent = Intent(this, AkunActivity::class.java)
            startActivity(intent)
        }
//        iv_kota.setOnClickListener {
//            val intent = Intent(this, TicketActivity::class.java)
//            startActivity(intent)
//        }
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

        recyclerViewkota.setOnClickListener {
            intent = Intent(this, DetailHotelActivity::class.java)
            startActivity(intent)
        }

        val kotaList = createKotaList()
        val adapter1 = KotaAdapter(kotaList)
        recyclerViewkota.adapter = adapter1
        adapter1.setOnItemClickListener(object : KotaAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val kota = kotaList[position]
                val intent = Intent(this@MainActivity, DetailHotelActivity::class.java)
                intent.putExtra("namakota", kota.namakota)
                startActivity(intent)
            }
        })

        val wisataList = createWisataList()
        val adapter2 = WisataAdapter(wisataList)
        recyclerViewWisata.adapter = adapter2
        adapter2.setOnItemClickListener(object : WisataAdapter.OnItemClickListener{
            override fun onItemClick(position: Int){
//                val intent = Intent(this@MainActivity, DetailHotelActivity::class.java)
                intent.putExtra("namawisata", createWisataList()[position].namawisata)
                startActivity(intent)
            }
        })

        drawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.menu_orders -> {
                    val intent = Intent(this, TicketActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_help -> {
                    // Handle item 2 click
                    // ...
                    true
                }
                // Add more menu items as needed
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    internal fun createWisataList(): MutableList<Wisata> {
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
