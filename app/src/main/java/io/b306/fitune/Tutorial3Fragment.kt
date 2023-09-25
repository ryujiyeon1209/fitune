package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import io.b306.fitune.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvExerciseList.apply {
            layoutManager = GridLayoutManager(context, 2) // 2개의 컬럼을 가진 그리드 레이아웃
            adapter = ExerciseAdapter(ExerciseList.list) // ExerciseAdapter는 별도로 정의해야 함
        }
    }

    companion object {
        fun newInstance() = Tutorial3Fragment()
    }
}