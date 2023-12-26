package com.example.kelompok6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kelompok6.databinding.ActivityMain2Binding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        replacefragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replacefragment(HomeFragment())
                R.id.akun -> replacefragment(AkunFragment())
                R.id.orders -> replacefragment(TicketFragment())
                else -> {
                }
            }
            true
        }

        // Tambahkan listener untuk menangani tombol kembali
        onBackPressedDispatcher.addCallback(this) {
            showExitConfirmationDialog()
        }

        // Perbarui waktu kadaluarsa sesi setiap kali pengguna berinteraksi
        updateSessionExpirationTime()
    }

    private fun replacefragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah Anda yakin ingin keluar?")
        builder.setPositiveButton("Ya") { _, _ ->
            // Jika pengguna mengklik "Ya", keluar dari aplikasi
            performLogout()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            // Jika pengguna mengklik "Tidak", tutup dialog
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun performLogout() {
        // Hapus data sesi dan arahkan ke layar login
        clearSessionData()
        redirectToLoginScreen()
    }

    private fun updateSessionExpirationTime() {
        val expirationTime = Calendar.getInstance().timeInMillis + SESSION_TIMEOUT
        sharedPreferences.edit().putLong(EXPIRATION_TIME_KEY, expirationTime).apply()
    }

    private fun clearSessionData() {
        // Hapus data sesi dari SharedPreferences
        sharedPreferences.edit().remove(EXPIRATION_TIME_KEY).apply()
    }

    private fun redirectToLoginScreen() {
        // Navigasi ke layar login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SESSION_TIMEOUT = 30 * 60 * 1000 // 30 menit
        private const val EXPIRATION_TIME_KEY = "expiration_time"
    }
}
