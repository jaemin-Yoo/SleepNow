package com.example.sleepnow.dialogs

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.sleepnow.R
import com.example.sleepnow.data.SleepTimeData
import com.example.sleepnow.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.dialog_time_list.*

class CustomDialog(context: Context){
    private val dialog = Dialog(context)

    fun showDialog(sleepTimeList: ArrayList<SleepTimeData>){
        dialog.setContentView(R.layout.dialog_time_list)

        // dialog 셋팅
        dialog.tv_time1.text = sleepTimeList[0].time
        dialog.tv_time2.text = sleepTimeList[1].time
        dialog.tv_time3.text = sleepTimeList[2].time
        dialog.tv_time4.text = sleepTimeList[3].time

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT) // 크기 설정
        dialog.setCanceledOnTouchOutside(true) // 영역 밖 클릭 시 다이얼로그 사라짐
        dialog.setCancelable(true) // Back키로 다이얼로그 닫기
        dialog.show()
    }
}