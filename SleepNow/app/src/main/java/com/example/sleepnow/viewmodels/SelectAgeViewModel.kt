package com.example.sleepnow.viewmodels

import android.app.Application
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SelectAgeViewModel(application: Application): AndroidViewModel(application) {
    var age = MutableLiveData<String>()

    init{
        age.value = "1살"
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