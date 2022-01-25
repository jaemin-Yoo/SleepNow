package com.example.sleepnow.viewmodels

import android.app.Application
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sleepnow.utils.MyApplication

class SelectAgeViewModel(application: Application): AndroidViewModel(application) {
    var age = MutableLiveData<String>()

    init{
        var curAge = MyApplication.prefs.getInt("age")
        if (curAge > 0){
            if (curAge >= 65){
                age.value = "${curAge}+살"
            } else{
                age.value = "${curAge}살"
            }
        } else{
            age.value = "나이를 선택해주세요."
        }
    }

    fun changeAge(seekBar: SeekBar){

        var progress = seekBar.progress
        if (progress >= 65){
            age.value = "${progress}+살"
        } else{
            age.value = "${progress}살"
        }
    }
}