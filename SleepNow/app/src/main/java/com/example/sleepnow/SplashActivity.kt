package com.example.sleepnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000 // (ms)초 간 Splash 화면 띄움

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // x초 뒤 SelectAgeActivity 이동
            val intent = Intent(this, SelectAgeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME)
    }
}