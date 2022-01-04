package com.example.sleepnow.viewmodels

import android.app.Application
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SelectAgeViewModel(application: Application): AndroidViewModel(application) {
    var age = MutableLiveData<Int>()

    init{
        age.value = 1
    }

    fun changeAge(seekBar: SeekBar){
        age.value = seekBar.progress
    }
}