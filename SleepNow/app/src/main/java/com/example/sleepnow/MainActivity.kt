package com.example.sleepnow

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sleepnow.databinding.ActivityMainBinding
import com.example.sleepnow.dialogs.CustomDialog
import com.example.sleepnow.utils.EventObserver
import com.example.sleepnow.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
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

        tab_state.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                model.changeTab()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        // ViewModel에서 다이얼로그를 띄우기 위한 EventWrapper
        val dialog = CustomDialog(this)
        model.showDialog.observe(this, EventObserver{
            dialog.showDialog(model.sleepTimeData)
        })

        setActionBar() // 액션바 설정
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_actions, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_setting -> {
                // 설정 이동
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun setActionBar(){
        // 액션바 아이콘 표시
        val ab = supportActionBar
        ab!!.setIcon(R.drawable.ic_moon)
        ab.setDisplayUseLogoEnabled(true)
        ab.setDisplayShowHomeEnabled(true)

        // 액션바 그림자 제거
        ab.elevation = 0F
    }
}