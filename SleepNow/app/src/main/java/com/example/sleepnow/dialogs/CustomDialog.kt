package com.example.sleepnow.dialogs

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.sleepnow.R

class CustomDialog(context: Context){
    private val dialog = Dialog(context)

    fun showDialog(){
        dialog.setContentView(R.layout.dialog_time_list)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT) // 크기 설정
        dialog.setCanceledOnTouchOutside(true) // 영역 밖 클릭 시 다이얼로그 사라짐
        dialog.setCancelable(true) // Back키로 다이얼로그 닫기
        dialog.show()
    }
}