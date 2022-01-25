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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(application: Application): AndroidViewModel(application) {

    // MutableLiveData: 바뀔 수 있는 데이터
    // LiveData: 변경 되지 않는 데이터 (생명주기를 알고있는 Observer)
    private val _showDialog = MutableLiveData<Event<Boolean>>()
    val showDialog: LiveData<Event<Boolean>> = _showDialog

    val sleepState = MutableLiveData<String>()

    var sleepTimeData = ArrayList<SleepTimeData>()

    init {
        sleepState.value = "자러 갈 시간을 확인하세요."
    }

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

    fun changeTab(){
        if (sleepState.value == "자러 갈 시간을 확인하세요."){
            sleepState.value = "일어 날 시간을 확인하세요."
        } else{
            sleepState.value = "자러 갈 시간을 확인하세요."
        }
    }

    private fun calculateWakeUpTime(hour: Int, min: Int, age: Int){

        var dateFormat = SimpleDateFormat("hh:mm a")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC") // 휴대폰마다 결과 값이 다른 문제 해결
        var sleepTimeList = arrayListOf<SleepTimeData>()
        var pickerMinute = hour * 60 + min

        // 나이별 수면시간
        var ONE_TWO_HOUR = 815.0
        var THREE_FIVE_HOUR = 765.0
        var SIX_THIRTEEN_HOUR = 665.0
        var FOURTEEN_SEVENTEEN_HOUR = 615.0
        var EIGHTEEN_SIXTY_FIVE_HOUR = 555.0

        // 나이별 수면 사이클
        var ONE_TWO_CYCLE = 16
        var THREE_FIVE_CYCLE = 15
        var SIX_THIRTEEN_CYCLE = 13
        var FOURTEEN_SEVENTEEN_CYCLE = 12
        var EIGHTEEN_SIXTY_FIVE_CYCLE = 6

        when(age){
            in 1..2 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + ONE_TWO_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", ONE_TWO_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    sleepTimeList.add(SleepTimeData(time, hour, ONE_TWO_CYCLE, true))

                    ONE_TWO_HOUR -= 50
                    ONE_TWO_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 3..5 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + THREE_FIVE_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", THREE_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    sleepTimeList.add(SleepTimeData(time, hour, THREE_FIVE_CYCLE, true))

                    THREE_FIVE_HOUR -= 50
                    ONE_TWO_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 6..13 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + SIX_THIRTEEN_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", SIX_THIRTEEN_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    // 추천 수면 시간
                    var suggested = i != 4
                    sleepTimeList.add(SleepTimeData(time, hour, SIX_THIRTEEN_CYCLE, suggested))

                    SIX_THIRTEEN_HOUR -= 50
                    SIX_THIRTEEN_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 14..17 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + FOURTEEN_SEVENTEEN_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", FOURTEEN_SEVENTEEN_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = i != 4
                    sleepTimeList.add(SleepTimeData(time, hour, FOURTEEN_SEVENTEEN_CYCLE, suggested))

                    FOURTEEN_SEVENTEEN_HOUR -= 50
                    FOURTEEN_SEVENTEEN_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 18..64 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + EIGHTEEN_SIXTY_FIVE_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", EIGHTEEN_SIXTY_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = !(i == 3 || i == 4)
                    sleepTimeList.add(SleepTimeData(time, hour, EIGHTEEN_SIXTY_FIVE_CYCLE, suggested))

                    EIGHTEEN_SIXTY_FIVE_HOUR -= 90
                    EIGHTEEN_SIXTY_FIVE_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            else -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute + EIGHTEEN_SIXTY_FIVE_HOUR
                    if (totalMinute >= 1440){
                        totalMinute -= 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", EIGHTEEN_SIXTY_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = i == 2
                    sleepTimeList.add(SleepTimeData(time, hour, EIGHTEEN_SIXTY_FIVE_CYCLE, suggested))

                    EIGHTEEN_SIXTY_FIVE_HOUR -= 90
                    EIGHTEEN_SIXTY_FIVE_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }
        }

        _showDialog.value = Event(true)
    }

    private fun calculateBedTime(hour: Int, min: Int, age: Int){

        var dateFormat = SimpleDateFormat("a hh:mm", Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        var sleepTimeList = arrayListOf<SleepTimeData>()
        var pickerMinute = hour * 60 + min

        // 나이별 수면시간
        var ONE_TWO_HOUR = 815.0
        var THREE_FIVE_HOUR = 765.0
        var SIX_THIRTEEN_HOUR = 665.0
        var FOURTEEN_SEVENTEEN_HOUR = 615.0
        var EIGHTEEN_SIXTY_FIVE_HOUR = 555.0

        // 나이별 수면 사이클
        var ONE_TWO_CYCLE = 16
        var THREE_FIVE_CYCLE = 15
        var SIX_THIRTEEN_CYCLE = 13
        var FOURTEEN_SEVENTEEN_CYCLE = 12
        var EIGHTEEN_SIXTY_FIVE_CYCLE = 6

        when(age){
            in 1..2 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - ONE_TWO_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    Log.d("MainViewModel", "totalMs: $totalMs")
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", ONE_TWO_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    sleepTimeList.add(SleepTimeData(time, hour, ONE_TWO_CYCLE, true))

                    ONE_TWO_HOUR -= 50
                    ONE_TWO_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 3..5 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - THREE_FIVE_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", THREE_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    sleepTimeList.add(SleepTimeData(time, hour, THREE_FIVE_CYCLE, true))

                    THREE_FIVE_HOUR -= 50
                    THREE_FIVE_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 6..13 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - SIX_THIRTEEN_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", SIX_THIRTEEN_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    // 추천 수면 시간
                    var suggested = i != 4
                    sleepTimeList.add(SleepTimeData(time, hour, SIX_THIRTEEN_CYCLE, suggested))

                    SIX_THIRTEEN_HOUR -= 50
                    SIX_THIRTEEN_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 14..17 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - FOURTEEN_SEVENTEEN_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", FOURTEEN_SEVENTEEN_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = i != 4
                    sleepTimeList.add(SleepTimeData(time, hour, FOURTEEN_SEVENTEEN_CYCLE, suggested))

                    FOURTEEN_SEVENTEEN_HOUR -= 50
                    FOURTEEN_SEVENTEEN_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            in 18..64 -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - EIGHTEEN_SIXTY_FIVE_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", EIGHTEEN_SIXTY_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = !(i == 3 || i == 4)
                    sleepTimeList.add(SleepTimeData(time, hour, EIGHTEEN_SIXTY_FIVE_CYCLE, suggested))

                    EIGHTEEN_SIXTY_FIVE_HOUR -= 90
                    EIGHTEEN_SIXTY_FIVE_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }

            else -> {
                for (i in 1..4){
                    var totalMinute = pickerMinute - EIGHTEEN_SIXTY_FIVE_HOUR
                    if (totalMinute < 0){
                        totalMinute += 1440
                    }

                    var totalMs = totalMinute * 60000
                    var time = dateFormat.format(totalMs)
                    Log.d("MainViewModel", "time: $time")

                    var hour = String.format("%.1f", EIGHTEEN_SIXTY_FIVE_HOUR / 60)
                    Log.d("MainViewModel", "hour: $hour")

                    var suggested = i == 2
                    sleepTimeList.add(SleepTimeData(time, hour, EIGHTEEN_SIXTY_FIVE_CYCLE, suggested))

                    EIGHTEEN_SIXTY_FIVE_HOUR -= 90
                    EIGHTEEN_SIXTY_FIVE_CYCLE -= 1
                }

                sleepTimeData = sleepTimeList
            }
        }

        _showDialog.value = Event(true)
    }
}