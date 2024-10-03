package com.example.broadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "Receptor"
    private val customBroadcastReceiver = CustomBroadcastReceiver()
    private lateinit var txtMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(TAG, "onCreate")

        txtMessage = findViewById(R.id.txtMessage)
    }

    fun updateUI(message: String) {
        txtMessage.text = "Último mensaje: $message" // Actualiza el TextView
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        val intentFilter = IntentFilter("com.example.broadcastreceiver.MY_BROADCAST")
        registerReceiver(customBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        unregisterReceiver(customBroadcastReceiver)
    }
}
