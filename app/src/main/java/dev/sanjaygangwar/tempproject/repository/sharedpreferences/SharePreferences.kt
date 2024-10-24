package dev.sanjaygangwar.tempproject.repository.sharedpreferences


import android.content.Context
import android.content.SharedPreferences


class AppSharePreference(mContext: Context) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val APP_SHARED_PREFS = mContext.applicationInfo.name

    init {
        sharedPreferences = mContext.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun clearAllPreferences() {
        editor.clear()
        editor.commit()
    }

    fun clearPreferences(key: String?) {
        editor.remove(key)
        editor.apply()
    }

    var isTemperatureFahrenheit: Boolean
        get() = sharedPreferences.getBoolean("isTemperatureFahrenheit", false)
        set(flag) {
            editor.putBoolean("isTemperatureFahrenheit", flag)
            editor.commit()
        }
    var isClockSound: Boolean
        get() = sharedPreferences.getBoolean("isClockSound", false)
        set(flag) {
            editor.putBoolean("isClockSound", flag)
            editor.commit()
        }
    var visitCount: Int
        get() = sharedPreferences.getInt("visitCount", 0)
        set(visitCount) {
            editor.putInt("visitCount", visitCount)
            editor.commit()
        }
}

