package com.example.sleepnow.etc

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *  리사이클러뷰 아이템 간 간격 설정
 */

class RecyclerDecoration(divHeight: Int): RecyclerView.ItemDecoration() {

    private var divHeight = 0

    init {
        this.divHeight = divHeight
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1){
            outRect.bottom = divHeight
        }
    }
}