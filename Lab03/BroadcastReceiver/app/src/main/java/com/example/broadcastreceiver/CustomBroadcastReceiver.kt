package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "Receptor"

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("com.example.broadcastreceiver.MESSAGE")
        Log.d(TAG, "Mensaje recibido: $message")
        Toast.makeText(context, "El mensaje fue recibido: $message", Toast.LENGTH_SHORT).show()

        if (context is MainActivity) {
            context.updateUI(message ?: "No message received")
        }
    }
}
