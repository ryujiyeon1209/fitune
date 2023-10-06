package io.b306.fitune.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import io.b306.fitune.R
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.ExerciseRequestBody
import io.b306.fitune.databinding.FragmentExerciseResultBinding
import io.b306.fitune.model.ExerciseData
import io.b306.fitune.model.getImageResourceByExerciseId
import io.b306.fitune.model.getKoreanNameByExerciseId
import io.b306.fitune.room.ExerciseRecordEntity
import io.b306.fitune.room.FituneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ExerciseResultFragment : Fragment() {

    private var _binding: FragmentExerciseResultBinding? = null
    private val binding get() = _binding!!
    private var exerciseData: ExerciseData? = null

    // Fragment 내부의 멤버 변수로 코루틴 스코프 정의
    private val viewModelScope = CoroutineScope(Dispatchers.Main + Job())

    // userSeq
    private var userSeq : Int = 0

    // room 에 저장할 운동 시간 - 한국시간으로 바꾸기 (실제 휴대폰 쓰니까 사용 x)
//    private var startTimeKst: String = ""
//    private var endTimeKst: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 데이타베입스 연결
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val exerciseRecordDao = FituneDatabase.getInstance(requireContext()).exerciseRecordDao()

        //room에서 가져온 내 아이디
        viewModelScope.launch {
            userSeq = myInfoDao.getUserSeq()
        }

        // 버전에 따른 메소드 지원
        exerciseData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("EXERCISE_DATA", ExerciseData::class.java)
        } else {
            arguments?.getParcelable("EXERCISE_DATA")
        }

        // 해당하는 이미지 리소스 가져오기
        val imageResId = getImageResourceByExerciseId(exerciseData?.exerciseSeq ?: 3)

        // 운동 시간 구하기
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val utcTimeZone = TimeZone.getTimeZone("UTC")
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")

        val startTime = exerciseData?.startTimeMillis?.let { sdf.parse(it) }
        val endTime = exerciseData?.endTimeMillis?.let { sdf.parse(it) }

        if (startTime != null && endTime != null) {
            val diffMillis = endTime.time - startTime.time
            val diffSeconds = (diffMillis / 1000).toInt()
            val hours = diffSeconds / 3600
            val minutes = (diffSeconds % 3600) / 60
            val seconds = diffSeconds % 60

            binding.tvExerciseResultExerciseTime.text = String.format(
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds
            )
//
//            // 한국 시간으로 바꾸기
//            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
//            val startTimeDate = exerciseData?.startTimeMillis?.let { originalFormat.parse(it) }
//            val endTimeDate = exerciseData?.endTimeMillis?.let { originalFormat.parse(it) }
//
//            // 9시간을 밀리초로 환산
//            val nineHoursInMillis = 9 * 60 * 60 * 1000
//
//            // Date에 9시간을 더하기
//            val adjustedStartTime = startTimeDate?.let { Date(it.time + nineHoursInMillis) }
//            val adjustedEndTime = endTimeDate?.let { Date(it.time + nineHoursInMillis) }
//
//            // 다시 String 형태로 변환하여 저장 혹은 다른 작업을 진행
//            startTimeKst = adjustedStartTime?.let { originalFormat.format(it) }.toString()
//            endTimeKst = adjustedEndTime?.let { originalFormat.format(it) }.toString()

        }

        val starButtons = listOf(
            binding.ibtnStar1,
            binding.ibtnStar2,
            binding.ibtnStar3,
            binding.ibtnStar4,
            binding.ibtnStar5,
        )

        val weatherButtons = listOf(
            binding.ibtnWeather1,
            binding.ibtnWeather2,
            binding.ibtnWeather3,
            binding.ibtnWeather4,
            binding.ibtnWeather5,
        )

        starButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                // 모든 버튼의 배경을 기본값으로 설정
                starButtons.forEach { it.background = null }
                // 클릭된 버튼에 테두리 설정
                it.setBackgroundResource(R.drawable.selected_image_border)
                // exerciseData.exerciseReview 값 설정
                exerciseData?.exerciseReview = index + 1
            }
        }

        weatherButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                // 모든 버튼의 배경을 기본값으로 설정
                weatherButtons.forEach { it.background = null }
                // 클릭된 버튼에 테두리 설정
                it.setBackgroundResource(R.drawable.selected_image_border)
                // exerciseData.exerciseReview 값 설정
                exerciseData?.exerciseWeather = index + 1
            }
        }

        // 바인딩으로 값 넣기
        binding.tvExerciseResultExercise.text = getKoreanNameByExerciseId(exerciseData?.exerciseSeq ?: 3)
        binding.ivExerciseResultExercise.setImageResource(imageResId)
        binding.tvExerciseResultAvgbpm.text = exerciseData?.avgHeartRate.toString()
        binding.tvExerciseResultMaxbpm.text = exerciseData?.maxHeartRate.toString()

        binding.btnExerciseResult.setOnClickListener {
            Log.e("최종로그",exerciseData.toString())
            val requestBody = exerciseData?.let { it ->
                ExerciseRequestBody(
                    exerciseListSeq = it.exerciseSeq,
                    recommended = true,
                    exerciseStart = it.startTimeMillis,
                    exerciseEnd = it.endTimeMillis,
                    exerciseAvgBpm = it.avgHeartRate,
                    exerciseMaxBpm = it.maxHeartRate,
                    review = it.exerciseReview,
                    weather = it.exerciseWeather
                )
            }

            // Retrofit 호출
            GlobalScope.launch(Dispatchers.IO) {
                val response = requestBody?.let { it1 ->
                    ApiObject.patchRetrofitExerciseService.postExerciseData(
                        userSeq = userSeq,
                        requestBody = it1
                    )
                }

                if (response != null) {
                    if (response.isSuccessful) {
                        // 성공적으로 API 호출 완료
                        // Room DB에 데이터 저장
                        val exerciseRecord = exerciseData?.let { it ->
                            ExerciseRecordEntity(
                                // 필요한 정보 입력
                                exerciseRecordSeq = it.exerciseSeq,
                                exerciseStart = it.startTimeMillis,
                                exerciseEnd = it.endTimeMillis,
                                exerciseReco = true,
                                exerciseAvgBpm = it.avgHeartRate,
                                exerciseMaxBpm = it.maxHeartRate,
                                exerciseDistance = 0,
                                exerciseReview = it.exerciseReview,
                                exerciseWeather = it.exerciseWeather
                            )
                        }
                        if (exerciseRecord != null) {
                            exerciseRecordDao.insert(exerciseRecord)
                        }
                        val newFragment = ExerciseHistoryFragment()
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.fm_container, newFragment)
                            ?.commit()
                    } else {
                        // API 호출 오류 처리
                    }
                }
            }

        }
    }

    companion object {
        fun newInstance() = ExerciseResultFragment()
    }
}