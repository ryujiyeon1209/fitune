package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.b306.fitune.databinding.FragmentExerciseResultBinding
import io.b306.fitune.databinding.FragmentExerciseSelectedBinding

private var _binding: FragmentExerciseSelectedBinding? = null
    private val binding get() = _binding!!
class ExerciseSelectedFragment : Fragment() {

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