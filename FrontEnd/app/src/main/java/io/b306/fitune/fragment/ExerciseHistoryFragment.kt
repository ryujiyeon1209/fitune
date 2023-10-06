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
    import io.b306.fitune.model.ExerciseList
    import io.b306.fitune.model.getImageResourceByExerciseId
    import io.b306.fitune.room.ExerciseRecordDao
    import io.b306.fitune.room.ExerciseRecordEntity
    import io.b306.fitune.room.ExerciseRecordRepository
    import io.b306.fitune.room.FituneDatabase
    import kotlinx.coroutines.launch
    import java.text.SimpleDateFormat
    import java.util.Calendar
    import java.util.Locale
    import kotlinx.coroutines.flow.first
    import java.util.Date

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
                if (exerciseRecords.isEmpty()) {
                    // Repository에서 가져온 데이터를 기반으로 달력 업데이트
                    exerciseRecords = exerciseRecordRepository.fetchAllExerciseRecord().first()
                }
                updateCalendar(calendar.get(Calendar.MONTH))
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
            val exerciseMap = exerciseRecords.associateBy(
                { it.exerciseStart!!.split("T")[0] }, { true }
            )

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

                    // 선택된 날짜에 대한 운동 기록 찾기
                    val selectedRecord = exerciseRecords.find { it.exerciseStart?.split("T")?.get(0) == selectedDate }

                    Log.e("DEBUG Calender", "Selected Record: $selectedRecord")
                    binding.tvHistoryDate.text = calendarDayModel.date.replace("-", ".")

                    if (selectedRecord != null && calendarDayModel.isCurrentMonth && calendarDayModel.day != 0) {

                        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
                        val startDate: Date = format.parse(selectedRecord.exerciseStart)
                        val endDate: Date = format.parse(selectedRecord.exerciseEnd)

                        // 두 날짜 간의 차이를 밀리초(ms) 단위로 계산
                        val differenceInMs = endDate.time - startDate.time

                        // 밀리초를 분으로 변환 (1분 = 60,000 밀리초)
                        val differenceInMinutes = differenceInMs / (1000 * 60)

                        val weatherImageResource = when (selectedRecord.exerciseWeather) {
                            1 -> R.drawable.ic_weather1
                            2 -> R.drawable.ic_weather2
                            3 -> R.drawable.ic_weather3
                            4 -> R.drawable.ic_weather4
                            5 -> R.drawable.ic_weather5
                            else -> R.drawable.ic_weather3  // 기타 값을 대비한 기본 텍스트
                        }

                        val reviewImageResource = when (selectedRecord.exerciseReview) {
                            1 -> R.drawable.ic_star_one
                            2 -> R.drawable.ic_star_two
                            3 -> R.drawable.ic_star_three
                            4 -> R.drawable.ic_star_four
                            5 -> R.drawable.ic_star_five
                            else -> R.drawable.ic_star_three
                        }

                        val startTime = selectedRecord.exerciseStart?.let { formatTimeString(it) }
                        val endTime = selectedRecord.exerciseEnd?.let { formatTimeString(it) }

                        // 운동 타입에 따라 이미지 변경
                        val imageResId = getImageResourceByExerciseId(selectedRecord.exerciseRecordSeq)

                        // TODO: 가져온 selectedRecord의 다른 정보들을 UI에 표시
                        binding.tvHistoryTotalTime.text = "$startTime - $endTime"
                        binding.ivHistoryType.setImageResource(imageResId)
                        binding.tvHistoryExerciseTime.text = "$differenceInMinutes 분"
                        binding.tvHistoryAvgHeart.text = selectedRecord.exerciseAvgBpm.toString()
                        binding.tvHistoryMaxHeart.text = selectedRecord.exerciseMaxBpm.toString()
                        binding.ivHistoryWeather.setImageResource(weatherImageResource)
                        binding.ivHistoryReview.setImageResource(reviewImageResource)
                    } else {
                        binding.tvHistoryTotalTime.text = ""
                        binding.ivHistoryType.setImageResource(0)
                        binding.tvHistoryExerciseTime.text = "-"
                        binding.tvHistoryAvgHeart.text = "-"
                        binding.tvHistoryMaxHeart.text = "-"
                        binding.ivHistoryWeather.setImageResource(0)
                        binding.ivHistoryReview.setImageResource(0)
                    }
                }
            })
        }

        fun formatTimeString(time: String): String? {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

            return inputFormat.parse(time)?.let { outputFormat.format(it) }
        }

        fun getExerciseNameByExerciseId(exerciseId: Int): String {
            val exercise = ExerciseList.list.find { it.exerciseId == exerciseId }
            return exercise?.name ?: "알 수 없음" // 해당 ID에 맞는 운동이 없으면 "알 수 없음" 반환
        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null // 메모리 누수 방지
        }
    }