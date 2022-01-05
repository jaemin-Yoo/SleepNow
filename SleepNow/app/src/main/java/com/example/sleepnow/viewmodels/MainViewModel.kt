package com.example.sleepnow.viewmodels

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.TimePicker
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sleepnow.data.SleepTimeData
import com.example.sleepnow.utils.Event
import com.example.sleepnow.utils.MyApplication
import com.google.android.material.tabs.TabLayout

class MainViewModel(application: Application): AndroidViewModel(application) {

    // MutableLiveData: 바뀔 수 있는 데이터
    // LiveData: 변경 되지 않는 데이터 (생명주기를 알고있는 Observer)
    private val _showDialog = MutableLiveData<Event<Boolean>>()
    val showDialog: LiveData<Event<Boolean>> = _showDialog

    var sleepTimeData = ArrayList<SleepTimeData>()

    fun getTime(timePicker: TimePicker, tabLayout: TabLayout){

        // 선택된 시간 가져오기
        var hour: Int
        var min: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.hour
            min = timePicker.minute
        } else {
            hour = timePicker.currentHour
            min = timePicker.currentMinute
        }
        Log.i("MainViewModel", "hour: $hour, min: $min")

        // 사용자 나이 가져오기
        var age = MyApplication.prefs.getInt("age")
        Log.i("MainViewModel", "age: $age")

        // 기상시간, 취침시간 상태 가져오기
        var tabState = tabLayout.selectedTabPosition // 0: 기상시간, 1: 취침시간

        // 수면시간 계산하기
        if (tabState == 0){
            calculateBedTime(hour, min, age) // 취침시간 계산
        } else{
            calculateWakeUpTime(hour, min, age) // 기상시간 계산
        }
    }

    private fun calculateWakeUpTime(hour: Int, min: Int, age: Int){

        var sleepTimeList = arrayListOf<SleepTimeData>()
        var pickerMinute = hour * 60 + min

        when(age){
            in 1..2 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 815
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 50
                }

                sleepTimeData = sleepTimeList
            }

            in 3..5 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 765
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 50
                }

                sleepTimeData = sleepTimeList
            }

            in 6..13 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 665
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 50
                }

                sleepTimeData = sleepTimeList
            }

            in 14..17 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 615
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 50
                }

                sleepTimeData = sleepTimeList
            }

            in 18..64 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 555
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 90
                }

                sleepTimeData = sleepTimeList
            }

            else -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + 555
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute -= 90
                }

                sleepTimeData = sleepTimeList
            }
        }

        _showDialog.value = Event(true)
    }

    private fun calculateBedTime(hour: Int, min: Int, age: Int){

        var sleepTimeList = arrayListOf<SleepTimeData>()
        var pickerMinute = hour * 60 + min

        when(age){
            in 1..2 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 815
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 50
                }

                sleepTimeData = sleepTimeList
            }

            in 3..5 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 765
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 50
                }

                sleepTimeData = sleepTimeList
            }

            in 6..13 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 665
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 50
                }

                sleepTimeData = sleepTimeList
            }

            in 14..17 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 615
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 50
                }

                sleepTimeData = sleepTimeList
            }

            in 18..64 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 555
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 90
                }

                sleepTimeData = sleepTimeList
            }

            else -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - 555
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    val calHour = totalMinute / 60
                    val calMin = totalMinute % 60
                    val time = "$calHour:$calMin"
                    Log.d("MainViewModel", "calHour: $calHour, calMin: $calMin")
                    sleepTimeList.add(SleepTimeData(time))

                    pickerMinute += 90
                }

                sleepTimeData = sleepTimeList
            }
        }

        _showDialog.value = Event(true)
    }
}