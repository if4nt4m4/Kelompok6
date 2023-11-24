package com.example.kelompok6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.kelompok6.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvLewati.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btDaftar.setOnClickListener{
            val namadepan = binding.etNamadepan.text.toString()
            val namablkng = binding.etNamablkng.text.toString()
            val email = binding.etEmailsignup.text.toString()
            val password = binding.etPwdsignup.text.toString()

            if (namadepan.isEmpty()){
                binding.etNamadepan.error = "Nama Depan Harus Diisi"
                binding.etNamadepan.requestFocus()
                return@setOnClickListener
            }

            if (namablkng.isEmpty()){
                binding.etNamablkng.error = "Nama Belakang Harus Diisi"
                binding.etNamablkng.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.etEmailsignup.error = "Email Harus Diisi"
                binding.etEmailsignup.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailsignup.error = "Email Tidak Valid"
                binding.etEmailsignup.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.etPwdsignup.error = "Password Harus Diisi"
                binding.etPwdsignup.requestFocus()
                return@setOnClickListener
            }

            if (password.length in 9..5){
                binding.etPwdsignup.error = "Password Harus 6-8"
                binding.etPwdsignup.requestFocus()
                return@setOnClickListener
            }

            SignupFirebase(email, password)
        }
    }

    private fun SignupFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Sign Up Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
