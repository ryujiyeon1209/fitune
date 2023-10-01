package io.b306.fitune.fragment

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.b306.fitune.R
import io.b306.fitune.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    interface TutorialPageNavigator {
        fun moveToNextPage()
    }

    private var pageNavigator: TutorialPageNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TutorialPageNavigator) {
            pageNavigator = context
        } else {
            throw RuntimeException("$context must implement TutorialPageNavigator")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnTutorial3Start = view.findViewById<Button>(R.id.btn_tutorial3Start)
        val remainingTimeTextView = view.findViewById<TextView>(R.id.remaining_time)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar_timer)

        var timer: CountDownTimer? = null
        var totalTimeMillis: Long = 180000 // 3분 (60초 * 1000밀리초)

        // 버튼 클릭 시 동작
        btnTutorial3Start.setOnClickListener {

            timer?.cancel()  // 타이머가 이미 작동 중인 경우 중지

            // 타이머 초기화
            timer = object : CountDownTimer(totalTimeMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // 남은 시간 업데이트
                    val secondsRemaining = millisUntilFinished / 1000
                    remainingTimeTextView.text = "남은 시간: $secondsRemaining 초"

                    // 프로그레스바 업데이트 (최대값: totalTimeMillis / 1000, 현재값: secondsRemaining)
                    progressBar.progress = (totalTimeMillis / 1000 - secondsRemaining).toInt()
                }

                override fun onFinish() {
                    remainingTimeTextView.text = "남은 시간: 0 초"
                }
            }

            timer?.start()  // 타이머 시작
        }


        // 버튼이 클릭되면 다음 페이지로 이동
        binding.btnTutorial3.setOnClickListener{
            pageNavigator?.moveToNextPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 바인딩 참조를 정리합니다.
        _binding = null
    }

    companion object {
        fun newInstance() = Tutorial3Fragment()
    }
}