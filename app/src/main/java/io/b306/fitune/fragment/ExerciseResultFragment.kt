package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.databinding.FragmentExerciseResultBinding

class ExerciseResultFragment : Fragment() {

    private var _binding: FragmentExerciseResultBinding? = null
    private val binding get() = _binding!!
    // 맑음 구름많음 비 눈 쌀쌀함

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기가 로직 작성하는 공간
    }

    companion object {
        fun newInstance() = ExerciseResultFragment()
    }
}