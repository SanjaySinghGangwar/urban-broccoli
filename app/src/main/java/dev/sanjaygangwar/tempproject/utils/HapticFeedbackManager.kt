package dev.sanjaygangwar.tempproject.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class HapticFeedbackManager(private val context: Context) {

    fun vibrate(milliseconds: Long =100) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
//                    vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.EFFECT_CLICK))
                }else{
                    vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(milliseconds)
            }
        }
    }
}
