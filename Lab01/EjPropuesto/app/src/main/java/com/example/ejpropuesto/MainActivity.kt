package com.example.ejpropuesto

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejpropuesto.databinding.ActivityMainBinding
import java.io.File

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

        val btnMostrar = binding.btnMostrar
        val btnRegistrar = binding.btnRegistrar

        val edtNombres = binding.edtNombres
        val edtApellidos = binding.edtApellidos
        val edtCorreo = binding.edtCorreo
        val edtTelefono = binding.edtTelefono
        val edtGrupoSanguineo = binding.edtGrupoSanguineo

        val fileName = "datos.txt"
        val fileDir = filesDir

        btnRegistrar.setOnClickListener {
            val nombres = edtNombres.text.toString()
            val apellidos = edtApellidos.text.toString()
            val correo = edtCorreo.text.toString()
            val telefono = edtTelefono.text.toString()
            val grupoSanguineo = edtGrupoSanguineo.text.toString()

            val file = File(fileDir, fileName)

            try {
                file.writeText(
                    "Nombres: $nombres\n" +
                            "Apellidos: $apellidos\n" +
                            "Correo: $correo\n" +
                            "Telefono: $telefono\n" +
                            "Grupo Sanguineo: $grupoSanguineo\n"
                )
                Toast.makeText(this, "Datos registrados exitosamente", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al registrar datos", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Error al escribir el archivo", e)
            }
        }

        btnMostrar.setOnClickListener {
            val file = File(fileDir, fileName)

            if (file.exists()) {
                try {
                    val content = file.readText()
                    Log.d(TAG, "Contenido del archivo:\n$content")
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al leer los datos", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error al leer el archivo", e)
                }
            } else {
                Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

}