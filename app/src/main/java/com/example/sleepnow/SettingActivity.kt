package com.example.sleepnow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sleepnow.databinding.ActivitySettingBinding
import com.example.sleepnow.utils.MyApplication
import com.example.sleepnow.viewmodels.SelectAgeViewModel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val model: SelectAgeViewModel by viewModels()

    private lateinit var result: ActivityResultLauncher<Intent>
    private var age = MyApplication.prefs.getInt("age")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        binding.lifecycleOwner = this
        binding.vm = model

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                var data = it.data?.getIntExtra("age", -1)

                if (data == 65){
                    tv_age.text = "${data}+살"
                } else{
                    tv_age.text = "${data}살"
                }
                age = MyApplication.prefs.getInt("age")
            }
        }

        btn_age.setOnClickListener {
            val intent = Intent(this, SelectAgeActivity::class.java)
            intent.putExtra("age", age)
            result.launch(intent)
        }

        setActionBar() // 액션바 설정
    }

    private fun setActionBar(){
        var actionBar = supportActionBar
        actionBar!!.title = "설정"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}