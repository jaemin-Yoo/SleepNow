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

        model.showDialog.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                val dialog = CustomDialog(this)
                dialog.showDialog()
            }
        })

        // 환경설정 이동
        btn_setting.setOnClickListener {
            val intent = Intent(this, SelectAgeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}