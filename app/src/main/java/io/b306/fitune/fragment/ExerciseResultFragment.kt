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
import io.b306.fitune.databinding.FragmentExerciseResultBinding
import io.b306.fitune.model.ExerciseData
import io.b306.fitune.model.getImageResourceByExerciseId
import io.b306.fitune.model.getKoreanNameByExerciseId
import java.text.SimpleDateFormat
import java.util.Locale

class ExerciseResultFragment : Fragment() {

    private var _binding: FragmentExerciseResultBinding? = null
    private val binding get() = _binding!!
    // 맑음 구름많음 비 눈 쌀쌀함
    private var exerciseData: ExerciseData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 버전에 따른 메소드 지원
        exerciseData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("EXERCISE_DATA", ExerciseData::class.java)
        } else {
            arguments?.getParcelable("EXERCISE_DATA")
        }

        Log.e("여기는 엑설리절트프래그멍트", exerciseData.toString())

        // 해당하는 이미지 리소스 가져오기
        val imageResId = getImageResourceByExerciseId(exerciseData?.exerciseSeq ?: 3)

        // 운동 시간 구하기
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())

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
        }
    }

    companion object {
        fun newInstance() = ExerciseResultFragment()
    }
}