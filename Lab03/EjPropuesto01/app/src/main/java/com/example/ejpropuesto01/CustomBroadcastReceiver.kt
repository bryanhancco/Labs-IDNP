package com.example.ejpropuesto01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class CustomBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "BATTERY_RECEPTOR"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Estado de batería recibido")
        if (context is MainActivity) {
            when (intent.action) {
                Intent.ACTION_BATTERY_CHANGED -> {
                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN)
                    val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10 // en grados Celsius

                    val batteryLevel = (level / scale.toFloat() * 100).toInt() // nivel de batería en porcentaje
                    val healthString = when (health) {
                        BatteryManager.BATTERY_HEALTH_GOOD -> "Buena"
                        BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Sobrecalentada"
                        BatteryManager.BATTERY_HEALTH_DEAD -> "Muerta"
                        else -> "Desconocida"
                    }

                    context.updateBatteryUI(batteryLevel, healthString, temperature)
                }
                else -> {
                    context.updateUI(intent.action ?: "")
                }
            }
        }
    }
}