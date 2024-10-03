package com.example.broadcastemisor

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "Emisor"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSendMessage = findViewById<Button>(R.id.btnSendMessage)
        val edtMessage = findViewById<EditText>(R.id.edtMessage)

        btnSendMessage.setOnClickListener {
            val message = edtMessage.text.toString()
            if (message.isNotEmpty()) {
                sendMessage(message)
            } else {
                Log.d(TAG, "Message is empty")
            }
        }
    }

    private fun sendMessage(message: String) {
        val intent = Intent("com.example.broadcastreceiver.MY_BROADCAST")
        intent.putExtra("com.example.broadcastreceiver.MESSAGE", message)

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Enviar el broadcast usando el PendingIntent
        try {
            pendingIntent.send()
            Toast.makeText(this, "El mensaje fue enviado: $message", Toast.LENGTH_SHORT).show()
        } catch (e: PendingIntent.CanceledException) {
            Log.e(TAG, "PendingIntent fue cancelado", e)
        }
    }
}
