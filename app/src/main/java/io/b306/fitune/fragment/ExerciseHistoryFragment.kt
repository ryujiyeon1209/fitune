    package io.b306.fitune.fragment

    import android.os.Bundle
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.lifecycleScope
    import androidx.recyclerview.widget.GridLayoutManager
    import io.b306.fitune.adapter.CalendarAdapter
    import io.b306.fitune.model.CalendarDayModel
    import io.b306.fitune.adapter.OnDayClickListener
    import io.b306.fitune.R
    import io.b306.fitune.databinding.FragmentExerciseHistoryBinding
    import io.b306.fitune.room.ExerciseRecordDao
    import io.b306.fitune.room.ExerciseRecordEntity
    import io.b306.fitune.room.ExerciseRecordRepository
    import io.b306.fitune.room.FituneDatabase
    import kotlinx.coroutines.launch
    import java.text.SimpleDateFormat
    import java.util.Calendar
    import java.util.Locale
    import kotlinx.coroutines.flow.first

    class ExerciseHistoryFragment : Fragment() {

        private var _binding: FragmentExerciseHistoryBinding? = null
        private val binding get() = _binding!!

        // 달력 사용
        private val calendar = Calendar.getInstance()
        private lateinit var exerciseRecordRepository: ExerciseRecordRepository
        private var exerciseRecords = listOf<ExerciseRecordEntity>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentExerciseHistoryBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Room Database 인스턴스 가져오기
            val database = context?.let { FituneDatabase.getInstance(it) }
            val exerciseRecordDao = database?.exerciseRecordDao()
            // Repository 초기화
            exerciseRecordRepository = exerciseRecordDao?.let { ExerciseRecordRepository(it) }!!

            lifecycleScope.launch {
                // Repository에서 가져온 데이터를 기반으로 달력 업데이트
                val exerciseRecords = exerciseRecordRepository.fetchAllExerciseRecord().first()

                // exerciseStart를 기준으로 exerciseMap 생성
                val exerciseMap = exerciseRecords.associateBy(
                    { it.exerciseStart!!.split("T")[0] }, { true }
                )

                updateCalendar(calendar.get(Calendar.MONTH), exerciseMap)
            }

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

        }

        private fun updateCalendar(month: Int, exerciseMap: Map<String, Boolean> = emptyMap()) {
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
            binding.rvCalendar.adapter = CalendarAdapter(yourListOfDays, exerciseMap, object : OnDayClickListener {
                override fun onDayClick(calendarDayModel: CalendarDayModel) {
//                    val tvDate = view?.findViewById<TextView>(R.id.tv_history_date)
//                    if (calendarDayModel.isCurrentMonth && calendarDayModel.day != 0) {
//                        tvDate?.text = calendarDayModel.date.replace("-", ".")
//                    }
                    val selectedDate = calendarDayModel.date
                    val tvDate = view?.findViewById<TextView>(R.id.tv_history_date)

                    // 선택된 날짜에 대한 운동 기록 찾기
                    val selectedRecord = exerciseRecords.find { it.exerciseStart?.split("T")?.get(0) == selectedDate }

                    Log.e("DEBUG Calender", "Selected Record: $selectedRecord")

                    if (selectedRecord != null && calendarDayModel.isCurrentMonth && calendarDayModel.day != 0) {
                        tvDate?.text = calendarDayModel.date.replace("-", ".")

                        // TODO: 가져온 selectedRecord의 다른 정보들을 UI에 표시
                        binding.tvHistoryType.text = selectedRecord.exerciseRecordSeq.toString()
//                        binding.tvHistoryTargetTime.text = // 목표 시간이 없음
                        binding.tvHistoryAvgHeart.text = selectedRecord.exerciseAvgBpm.toString()
                        binding.tvHistoryMaxHeart.text = selectedRecord.exerciseMaxBpm.toString()
                    } else {
                        binding.tvHistoryType.text = "a"
//                        binding.tvHistoryTargetTime.text = // 목표 시간이 없음
                        binding.tvHistoryAvgHeart.text = "b"
                        binding.tvHistoryMaxHeart.text = "c"
                    }
                }
            })
        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null // 메모리 누수 방지
        }
    }