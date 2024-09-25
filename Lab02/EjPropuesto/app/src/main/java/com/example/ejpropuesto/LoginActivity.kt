package com.example.ejpropuesto

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejpropuesto.databinding.ActivityLoginBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val FILE_NAME = "cuentas.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogin = binding.btnLogin
        val btnRegister = binding.btnRegister
        val edtUsername = binding.edtUsername
        val edtPassword = binding.edtPassword

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            val firstName = checkCredentials(username, password)

            if (firstName != null) {
                Toast.makeText(applicationContext, "Bienvenido $firstName", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Bienvenido a mi App")
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("FIRST_NAME", firstName)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Cuenta no encontrada", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Cuenta no encontrada")
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    private var activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        object : ActivityResultCallback<ActivityResult> {
            override fun onActivityResult(activityResult: ActivityResult) {
                val resultCode = activityResult.resultCode

                if (resultCode == AccountActivity.ACCOUNT_ACCEPT) {
                    val data = activityResult.data
                    val accountData = data?.getStringExtra(AccountActivity.ACCOUNT_RECORD)

                    val gson = Gson()
                    val account = gson.fromJson(accountData, AccountEntity::class.java)
                    saveCredentials(account)

                    Toast.makeText(applicationContext, "Cuenta creada: ${account.firstName}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Registro cancelado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )

    private fun saveCredentials(account: AccountEntity) {
        val file = File(filesDir, "cuentas.txt")
        try {
            val writer = FileWriter(file, true) // 'true' para agregar al final del archivo
            writer.append("${account.firstName},${account.lastName},${account.email},${account.phone},${account.username},${account.password}\n")
            writer.flush()
            writer.close()
            Log.d(TAG, "Credenciales guardadas: ${account.username}")
        } catch (e: IOException) {
            Log.e(TAG, "Error al guardar las credenciales", e)
        }
    }

    private fun checkCredentials(username: String, password: String): String? {
        val file = File(filesDir, "cuentas.txt")
        if (!file.exists()) {
            Log.e(TAG, "El archivo cuentas.txt no existe")
            return null
        }

        return try {
            BufferedReader(file.inputStream().bufferedReader()).use { reader ->
                reader.lineSequence().forEach { line ->
                    val credentials = line.split(",")
                    if (credentials.size >= 6 && credentials[4] == username && credentials[5] == password) {
                        return credentials[0] // Retorna firstName
                    }
                }
            }
            null
        } catch (e: Exception) {
            Log.e(TAG, "Error al leer el archivo de cuentas", e)
            null
        }
    }

}
