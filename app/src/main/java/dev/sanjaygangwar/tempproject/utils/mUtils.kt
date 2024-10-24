package dev.sanjaygangwar.tempproject.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object mUtils {
    fun Context.mToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun mLog(msg:String){
        Log.i("SANJAY", "mLog: -----------------------------------------")
        Log.i("SANJAY", msg)
        Log.i("SANJAY", "mLog: -----------------------------------------")
    }
}