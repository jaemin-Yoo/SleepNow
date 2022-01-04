package com.example.sleepnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.sleepnow.databinding.ActivitySelectAgeBinding
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

        btn_next.setOnClickListener {
            // MainActivity 이동
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}