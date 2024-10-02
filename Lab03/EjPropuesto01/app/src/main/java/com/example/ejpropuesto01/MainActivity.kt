package com.example.ejpropuesto01

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "BATTERY_RECEPTOR"
    private val customBroadcastReceiver = CustomBroadcastReceiver()

    private lateinit var textBatCon: TextView
    private lateinit var textBatStat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textBatCon = findViewById(R.id.battery_connected)
        textBatStat = findViewById(R.id.battery_status)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume, registro satisfactorio")
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }
        registerReceiver(customBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause, desregistro satisfactorio")
        unregisterReceiver(customBroadcastReceiver)
    }

    // Método para actualizar la UI desde el BroadcastReceiver
    fun updateUI(action: String) {
        when (action) {
            Intent.ACTION_POWER_CONNECTED -> {
                textBatCon.text = "Batería en carga: Sí"
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                textBatCon.text = "Batería en carga: No"
            }
            Intent.ACTION_BATTERY_CHANGED -> {
                textBatStat.text = "Estado de batería: Ha cambiado"
            }
            Intent.ACTION_BATTERY_LOW -> {
                textBatStat.text = "Estado de batería: Batería baja"
            }
            Intent.ACTION_BATTERY_OKAY -> {
                textBatStat.text = "Estado de batería: Batería en buen estado"
            }
        }
    }
}