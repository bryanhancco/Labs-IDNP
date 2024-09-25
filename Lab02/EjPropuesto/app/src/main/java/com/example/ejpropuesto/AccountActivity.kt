package com.example.ejpropuesto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejpropuesto.databinding.ActivityAccountBinding
import com.google.gson.Gson

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    companion object {
        const val ACCOUNT_RECORD = "ACCOUNT_RECORD"
        const val ACCOUNT_ACCEPT = 100
        const val ACCOUNT_CANCEL = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAceptar = binding.btnAceptar
        val btnCancelar = binding.btnCancelar

        val edtNombres = binding.edtNombres
        val edtApellidos = binding.edtApellidos
        val edtCorreo = binding.edtCorreo
        val edtTelefono = binding.edtTelefono
        val edtUsuario = binding.edtUsuario
        val edtPassword = binding.edtPassword

        btnAceptar.setOnClickListener {
            val account = AccountEntity().apply {
                firstName = edtNombres.text.toString()
                lastName = edtApellidos.text.toString()
                email = edtCorreo.text.toString()
                phone = edtTelefono.text.toString()
                username = edtUsuario.text.toString()
                password = edtPassword.text.toString()
            }


            var gson = Gson()
            var accountJson = gson.toJson(account)

            var data = Intent()
            data.putExtra(ACCOUNT_RECORD, accountJson)
            setResult(ACCOUNT_ACCEPT, data)
            finish()

        }

        btnCancelar.setOnClickListener {
            setResult(ACCOUNT_CANCEL)
            finish()
        }
    }


}