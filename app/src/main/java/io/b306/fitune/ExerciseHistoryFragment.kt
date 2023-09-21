package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.databinding.FragmentExerciseHistoryBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ExerciseHistoryFragment : Fragment() {

    private var _binding: FragmentExerciseHistoryBinding? = null
    private val binding get() = _binding!!

    // 달력 사용
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 달력 이전 버튼
        binding.btnPreviousMonth.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            updateCalendar(calendar.get(Calendar.MONTH))
        }

        // 달력 다음 버튼
        binding.btnNextMonth.setOnClickListener {
            // calendar.add 안 쓰고 바로 updateCalendar의 MONTH를 -1, +1 하면
            // 동작이 안 됨
            // -> 단순히 MONTH의 필드 값을 변경하는 것이므로
            // 다른 달력 필드들에 어떤 영향을 미칠지 모름
            calendar.add(Calendar.MONTH, 1)
            updateCalendar(calendar.get(Calendar.MONTH))
        }

        updateCalendar(calendar.get(Calendar.MONTH))
    }

    private fun updateCalendar(month: Int) {
        calendar.set(Calendar.MONTH, month)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // 0-based index
        val currentYear = calendar.get(Calendar.YEAR)

        binding.tvCurrentMonthYear?.text = "${currentYear}년 ${currentMonth}월"

        // 달력 데이터를 생성합니다.
        val yourListOfDays = mutableListOf<CalendarDayModel>()

        // 달력의 첫 날과 마지막 날을 설정합니다.
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // 첫 날 전에는 0을 삽입합니다.
        for (i in 0 until firstDayOfMonth) {
            yourListOfDays.add(CalendarDayModel(0, false, ""))
        }

        for (i in 1..lastDayOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i)
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            yourListOfDays.add(CalendarDayModel(i, true, dateStr))
        }

        binding.rvCalendar.layoutManager = context?.let { GridLayoutManager(it, 7) } // 7일/주
        binding.rvCalendar.adapter = CalendarAdapter(yourListOfDays, object : OnDayClickListener {
            override fun onDayClick(calendarDayModel: CalendarDayModel) {
                val tvDate = view?.findViewById<TextView>(R.id.tv_date)
                if (calendarDayModel.isCurrentMonth && calendarDayModel.day != 0) {
                    tvDate?.text = calendarDayModel.date.replace("-", ".")
                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지
    }
}