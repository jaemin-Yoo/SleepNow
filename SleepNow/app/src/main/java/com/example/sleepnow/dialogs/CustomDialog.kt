package com.example.sleepnow.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sleepnow.R
import com.example.sleepnow.adapters.TimeListAdapter
import com.example.sleepnow.data.SleepTimeData
import com.example.sleepnow.etc.RecyclerDecoration
import kotlinx.android.synthetic.main.dialog_time_list.*

class CustomDialog(context: Context){
    private val dialog = Dialog(context)
    private val context = context

    fun showDialog(sleepTimeList: ArrayList<SleepTimeData>){
        dialog.setContentView(R.layout.dialog_time_list)

        // 리사이클러뷰 설정
        val mAdapter = TimeListAdapter(context)
        var spaceDecoration = RecyclerDecoration(30)
        dialog.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(spaceDecoration) // 아이템 간 간격 설정
        }

        mAdapter.setTimeList(sleepTimeList)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT) // 크기 설정
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 투명 배경 (둥근 배경 적용을 위해)
        dialog.setCanceledOnTouchOutside(true) // 영역 밖 클릭 시 다이얼로그 사라짐
        dialog.setCancelable(true) // Back키로 다이얼로그 닫기
        dialog.show()

        dialog.btn_exit.setOnClickListener {
            dialog.dismiss()
        }
    }
}