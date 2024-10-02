package com.example.ejpropuesto01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.io.OutputStreamWriter

class CustomBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "BATTERY_RECEPTOR"

    companion object {
        const val BATTERY_STATUS = "com.example.ejpropuesto01.BATTERY_STATUS"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Estado de bateria")
        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {

            }
            Intent.ACTION_POWER_DISCONNECTED -> {

            }
            Intent.ACTION_BATTERY_CHANGED -> {

            }
            Intent.ACTION_BATTERY_LOW -> {

            }
            Intent.ACTION_BATTERY_OKAY -> {

            }
        }
    }

}