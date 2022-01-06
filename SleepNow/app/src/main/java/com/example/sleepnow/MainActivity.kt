package com.example.sleepnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.sleepnow.databinding.ActivityMainBinding
import com.example.sleepnow.dialogs.CustomDialog
import com.example.sleepnow.utils.EventObserver
import com.example.sleepnow.utils.MyApplication
import com.example.sleepnow.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = model

        // 슬라이드 탭 추가
        tab_state.addTab(tab_state.newTab().setText("기상시간"))
        tab_state.addTab(tab_state.newTab().setText("취침시간"))

        // ViewModel에서 다이얼로그를 띄우기 위한 EventWrapper
        val dialog = CustomDialog(this)
        model.showDialog.observe(this, EventObserver{
            dialog.showDialog(model.sleepTimeData)
        })

        // 환경설정 이동
        btn_setting.setOnClickListener {
            val intent = Intent(this, SelectAgeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}