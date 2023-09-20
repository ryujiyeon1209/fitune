package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ExerciseHistoryActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_history)

        val btnPreviousMonth = findViewById<ImageButton>(R.id.btn_previousMonth)
        val btnNextMonth = findViewById<ImageButton>(R.id.btn_nextMonth)

        // 달력 이전 버튼
        btnPreviousMonth.setOnClickListener {
            updateCalendar(calendar.get(Calendar.MONTH) - 1)
        }

        // 달력 다음 버튼
        btnNextMonth.setOnClickListener {
            updateCalendar(calendar.get(Calendar.MONTH) + 1)
        }

        updateCalendar(calendar.get(Calendar.MONTH))
    }
    private fun updateCalendar(month: Int) {
        calendar.set(Calendar.MONTH, month)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // 0-based index
        val currentYear = calendar.get(Calendar.YEAR)

        val currentMonthYearTextView: TextView = findViewById(R.id.tv_currentMonthYear)
        currentMonthYearTextView.text = "${currentYear}년 ${currentMonth}월"

        // 달력 데이터를 생성합니다.
        val yourListOfDays = mutableListOf<CalendarDay>()

        // 달력의 첫 날과 마지막 날을 설정합니다.
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // 첫 날 전에는 0을 삽입합니다.
        for (i in 0 until firstDayOfMonth) {
            yourListOfDays.add(CalendarDay(0, false, ""))
        }

        for (i in 1..lastDayOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i)
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            yourListOfDays.add(CalendarDay(i, true, dateStr))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 7) // 7일/주
        recyclerView.adapter = CalendarAdapter(yourListOfDays)
    }
}