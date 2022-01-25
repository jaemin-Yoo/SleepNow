package com.example.sleepnow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.sleepnow.utils.MyApplication

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000 // (ms)초 간 Splash 화면 띄움

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // x초 뒤 Activity 이동
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // 나이 선택 여부 확인
            val age = MyApplication.prefs.getInt("age")
            val intent = if (age == -1){
                Intent(this, SelectAgeActivity::class.java)
            } else{
                Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME)
    }
}