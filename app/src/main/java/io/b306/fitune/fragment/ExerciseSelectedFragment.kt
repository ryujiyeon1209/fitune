package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    companion object {
        fun newInstance() = ExerciseSelectedFragment()
    }
}