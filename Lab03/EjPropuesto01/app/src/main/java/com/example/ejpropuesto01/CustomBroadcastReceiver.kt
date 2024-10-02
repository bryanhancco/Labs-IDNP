package com.example.ejpropuesto01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CustomBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "BATTERY_RECEPTOR"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Estado de batería recibido")
        if (context is MainActivity) {
            context.updateUI(intent.action ?: "")
        }
    }
}