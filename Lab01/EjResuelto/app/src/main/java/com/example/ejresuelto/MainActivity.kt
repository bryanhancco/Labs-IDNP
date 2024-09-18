package com.example.ejresuelto

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejresuelto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        val edtUsername = binding.edtUsername
        val edtPassword = binding.edtPassword

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if (username == "admin" && password == "admin") {
                Toast.makeText(applicationContext, "Bienvenido a mi App", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Bienvenido a mi App")
            } else {
                Toast.makeText(applicationContext, "Error en la autenticacion", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error en la autenticacion")
            }
        }
    }

}