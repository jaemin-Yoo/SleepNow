package com.example.sleepnow.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepnow.data.SleepTimeData
import com.example.sleepnow.databinding.RecyclerTimeItemBinding

class TimeListAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<TimeListAdapter.TimeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var sleepTimeList = emptyList<SleepTimeData>()

    inner class TimeViewHolder(private val binding: RecyclerTimeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(sleepTimeList: SleepTimeData){
            binding.list = sleepTimeList
            binding.tvHour.text = "수면시간: ${sleepTimeList.hour}"
            binding.tvCycle.text = "수면싸이클: ${sleepTimeList.cycle}"

            if (sleepTimeList.suggested){
                binding.icSuggested.visibility = View.VISIBLE
            } else{
                binding.icSuggested.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val binding = RecyclerTimeItemBinding.inflate(inflater, parent, false)
        return TimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(sleepTimeList[position])
    }

    internal fun setTimeList(sleepTimeList: List<SleepTimeData>) {
        this.sleepTimeList = sleepTimeList
        notifyDataSetChanged()
    }

    override fun getItemCount() = sleepTimeList.size
}