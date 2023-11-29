package com.example.kelompok6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kelompok6.databinding.ActivityDetailPembayaranBinding

class DetailPembayaranActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembayaran)
    }
}