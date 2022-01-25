package com.example.sleepnow.utils

import android.app.Application

/**
 * 가장 먼저 실행
 */
class MyApplication: Application() {
    companion object{
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}