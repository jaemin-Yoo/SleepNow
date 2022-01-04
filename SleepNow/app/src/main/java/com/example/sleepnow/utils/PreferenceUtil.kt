package com.example.sleepnow.utils

import android.content.Context
import android.content.SharedPreferences

/**
 *  사용자 나이 저장을 위한 SharedPreference Util
 */
class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String): String {
        return prefs.getString(key, "no value").toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, -1)
    }

    fun setInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }
}