package com.example.sleepnow

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.sleepnow.databinding.ActivitySelectAgeBinding
import com.example.sleepnow.utils.MyApplication
import com.example.sleepnow.viewmodels.SelectAgeViewModel
import kotlinx.android.synthetic.main.activity_select_age.*

class SelectAgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectAgeBinding
    private val model: SelectAgeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_age)
        binding.lifecycleOwner = this
        binding.vm = model

        // 나이 셋팅
        val age = MyApplication.prefs.getInt("age")
        if (age > 0){
            sb_age.progress = age
            model.changeAge(sb_age)
        }

        btn_next.setOnClickListener {
            // 나이 저장
            val changeAge = sb_age.progress
            MyApplication.prefs.setInt("age", changeAge)

            if (age == -1){
                // MainActivity 이동
                // 앱 첫 실행 시
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                val intent = Intent()
                intent.putExtra("age", changeAge)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        changeTextColor() // 특정 글자 색상 변경
        setActionBar() // 액션바 설정
    }

    private fun changeTextColor(){
        var ssb = SpannableStringBuilder(tv_title.text)
        ssb.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.sky)), 4, 6, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        //ssb.setSpan(ForegroundColorSpan(Color.parseColor("#58CCFF")), 4, 6, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_title.text = ssb
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