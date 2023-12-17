package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AkunActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvNamaAkun: EditText
    private lateinit var tvUsername: EditText
    private lateinit var tvNoHp: EditText
    private lateinit var tvEmail: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        tvNamaAkun = findViewById(R.id.et_namaakun)
        tvUsername = findViewById(R.id.et_username)
        tvNoHp = findViewById(R.id.et_nohp)
        tvEmail = findViewById(R.id.et_email)

        displayUserInfo()

        val btnLogout = findViewById<Button>(R.id.bt_logout)
        btnLogout.setOnClickListener {
            btnLogout()
        }

        val btnSimpan = findViewById<Button>(R.id.bt_simpan)
        btnSimpan.setOnClickListener {
            simpanInformasiAkun()
        }
    }

    private fun displayUserInfo() {
        val user = auth.currentUser

        if (user != null) {
            val userId = user.uid

            databaseReference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val namaAkun = snapshot.child("namaAkun").getValue(String::class.java)
                        val username = snapshot.child("username").getValue(String::class.java)
                        val noHp = snapshot.child("noHp").getValue(String::class.java)

                        tvNamaAkun.setText(namaAkun)
                        tvUsername.setText(username)
                        tvNoHp.setText(noHp)
                        tvEmail.setText(user.email)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                    Toast.makeText(this@AkunActivity, "Gagal mengambil informasi akun", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun simpanInformasiAkun() {
        val user = auth.currentUser

        if (user != null) {
            val userId = user.uid
            val namaAkun = tvNamaAkun.text.toString()
            val username = tvUsername.text.toString()
            val noHp = tvNoHp.text.toString()

            val userInfo = HashMap<String, Any>()
            userInfo["namaAkun"] = namaAkun
            userInfo["username"] = username
            userInfo["noHp"] = noHp

            databaseReference.child(userId).updateChildren(userInfo)
                .addOnSuccessListener {
                    Toast.makeText(this, "Informasi akun berhasil disimpan", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan informasi akun", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun btnLogout() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        finish()
    }
}
