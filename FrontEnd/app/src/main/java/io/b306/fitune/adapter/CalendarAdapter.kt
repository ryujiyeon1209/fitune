package io.b306.fitune.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.model.CalendarDayModel
import io.b306.fitune.R

interface OnDayClickListener {
    fun onDayClick(calendarDayModel: CalendarDayModel)
}
class CalendarAdapter(
    private val days: List<CalendarDayModel>,
    private val exerciseMap: Map<String, Boolean>,
    private val onDayClickListener: OnDayClickListener
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDay: TextView = view.findViewById(R.id.tv_day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val day = days[position]

        if (day.isCurrentMonth) {
            holder.tvDay.text = day.day.toString()

            if (exerciseMap[day.date] == true) { // date는 CalendarDay의 멤버 변수, String 타입으로 "yyyy-MM-dd" 형태
                holder.tvDay.setTextColor(Color.BLACK)
                holder.itemView.setBackgroundResource(R.drawable.item_circle_green_bg)
            } else {
                holder.tvDay.setTextColor(Color.GRAY)
                holder.itemView.background = null
            }

        } else {
            holder.tvDay.text = "" // 빈 문자열로 설정
        }

        holder.itemView.setOnClickListener {
            onDayClickListener.onDayClick(day)
        }
    }

    override fun getItemCount() = days.size
}