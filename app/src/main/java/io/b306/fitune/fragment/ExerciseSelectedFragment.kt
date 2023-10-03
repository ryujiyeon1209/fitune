package io.b306.fitune.fragment

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.activity.ExerciseProgressActivity
import io.b306.fitune.databinding.FragmentExerciseSelectedBinding


class ExerciseSelectedFragment : Fragment(), ExerciseProgressActivity.ExerciseSelectedListener {

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
    }

    companion object {
        fun newInstance() = ExerciseSelectedFragment()
    }

    override fun onExerciseStartButtonClicked() {
        //ExerciseProgressActivity로 이동, 현재시간 주기!
        val intent = Intent(activity, ExerciseProgressActivity::class.java)
        startActivity(intent)
    }
}