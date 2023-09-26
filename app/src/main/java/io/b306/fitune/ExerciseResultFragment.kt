package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.b306.fitune.databinding.FragmentExerciseResultBinding
import io.b306.fitune.databinding.FragmentFightFindBinding

class ExerciseResultFragment : Fragment() {

    private var _binding: FragmentExerciseResultBinding? = null
    private val binding get() = _binding!!

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