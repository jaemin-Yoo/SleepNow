package com.example.sleepnow.dialogs

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.sleepnow.R
import com.example.sleepnow.data.SleepTimeData
import com.example.sleepnow.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.dialog_time_list.*
import java.text.SimpleDateFormat

class CustomDialog(context: Context){
    private val dialog = Dialog(context)

    fun showDialog(sleepTimeList: ArrayList<SleepTimeData>){
        dialog.setContentView(R.layout.dialog_time_list)

        // String to Date, ex) time = "12:00"
        var dateParse = SimpleDateFormat("hh:mm")
        var time1 = dateParse.parse(sleepTimeList[0].time)
        var time2 = dateParse.parse(sleepTimeList[1].time)
        var time3 = dateParse.parse(sleepTimeList[2].time)
        var time4 = dateParse.parse(sleepTimeList[3].time)

        // dialog 셋팅
        var dateFormat = SimpleDateFormat("hh:mm a")
        dialog.tv_time1.text = dateFormat.format(time1)
        dialog.tv_time2.text = dateFormat.format(time2)
        dialog.tv_time3.text = dateFormat.format(time3)
        dialog.tv_time4.text = dateFormat.format(time4)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT) // 크기 설정
        dialog.setCanceledOnTouchOutside(true) // 영역 밖 클릭 시 다이얼로그 사라짐
        dialog.setCancelable(true) // Back키로 다이얼로그 닫기
        dialog.show()

        dialog.btn_exit.setOnClickListener {
            dialog.dismiss()
        }
    }
}