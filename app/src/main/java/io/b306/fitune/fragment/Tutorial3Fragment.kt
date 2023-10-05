package io.b306.fitune.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.R
import io.b306.fitune.activity.TutorialsActivity
import io.b306.fitune.databinding.FragmentTutorial3Binding
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!
    private lateinit var heartRateTextView: TextView
    private var heartRate=0

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
        heartRateTextView = binding.heartRateTextView

        val tutorialsActivity = activity as TutorialsActivity

        tutorialsActivity.nowHeartRate.observe(viewLifecycleOwner) { hearRate ->
            heartRateTextView.text = "$hearRate bpm"
            heartRate = hearRate.toInt()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnTutorial3Start = view.findViewById<Button>(R.id.btn_tutorial3Start)
        val remainingTimeTextView = view.findViewById<TextView>(R.id.remaining_time)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progressDrawable = ContextCompat.getDrawable(requireContext(),
            R.drawable.circular_progress_bar)

        val totalTimeMillis: Long = 10000 // 3분 (60초 * 1000밀리초)
        val intervalMillis: Long = 1000 // 1초

        // 초당 갱신되는 양 (프로그레스 막대에 표시될 초록색 양)
        val progressPerSecond = 1f // 초당 1초씩 채우기

        // 프로그레스 바의 최대값 설정 (프로그레스 막대가 초록색으로 완전히 채워지는 데 필요한 총 시간을 계산)
        progressBar.max = (totalTimeMillis / (intervalMillis * progressPerSecond)).toInt()

        // 초기에 프로그레스바를 회색으로 설정
        progressBar.progress = 0

        // 버튼 클릭 시 동작
        btnTutorial3Start.setOnClickListener {

            // 타이머 초기화
            val timer = object : CountDownTimer(totalTimeMillis, intervalMillis) {
                override fun onTick(millisUntilFinished: Long) {
                    // 남은 시간 업데이트
                    val secondsRemaining = millisUntilFinished / 1000 + 1
                    remainingTimeTextView.text = "남은 시간: $secondsRemaining 초"

                    // 프로그레스바 업데이트
                    val currentProgress = ((totalTimeMillis - millisUntilFinished) / (intervalMillis * progressPerSecond)).toInt()
                    progressBar.progress = currentProgress
                }

                override fun onFinish() {
                    remainingTimeTextView.text = "남은 시간: 0 초"
                    progressBar.progress = progressBar.max // 타이머가 끝났을 때 프로그레스 바를 최대 값으로 설정
                    AlertDialog.Builder(context).apply {
                        setTitle("심박수 설정")
                        // room 에다가 이거 넣어야 대

                        setMessage("활동 심박수를 ${heartRateTextView.text}로 설정하시겠습니까?")
                        setPositiveButton("예") { _, _ ->
                            // "예" 버튼을 누르면 다음 페이지로 이동
                            lifecycleScope.launch(Dispatchers.IO) {
                                val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
                                val myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()

                                myInfoEntity.activeBpm = heartRate;

                                if (myInfoEntity.id == 1) {
                                    myInfoDao.update(myInfoEntity)
                                    Log.d("업데이트 유저",myInfoEntity.toString())
                                } else {
                                    myInfoDao.insert(myInfoEntity)
                                    Log.d("유저 넣었습니다.",myInfoEntity.toString())
                                }
                            }
                            pageNavigator?.moveToNextPage()
                        }
                        setNegativeButton("아니오", null)
                    }.show()
                }
            }

            timer.start()  // 타이머 시작
        }

        // 버튼이 클릭되면 다음 페이지로 이동
//        binding.btnTutorial3.setOnClickListener{
//            pageNavigator?.moveToNextPage()
//        }
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