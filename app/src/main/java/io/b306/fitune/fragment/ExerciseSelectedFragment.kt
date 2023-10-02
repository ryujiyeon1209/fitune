package io.b306.fitune.fragment

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import io.b306.fitune.databinding.FragmentExerciseSelectedBinding


class ExerciseSelectedFragment : Fragment() {

    private var _binding: FragmentExerciseSelectedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로직 작성하는 곳
        setContentView(R.layout.activity_exercise_selected)

        // 버튼 초기화 및 클릭 리스너 설정
        val startExerciseButton: Button = findViewById(R.id.btn_exerciseStart)
        startExerciseButton.setOnClickListener { // ExerciseProgressActivity로 이동
            val intent = Intent(this@ExerciseSelectedActivity, ExerciseProgressActivity::class.java)

            // 현재 시간을 ExerciseProgressActivity로 전달
            val startTime = System.currentTimeMillis()
            intent.putExtra("startTime", startTime)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = ExerciseSelectedFragment()
    }
}