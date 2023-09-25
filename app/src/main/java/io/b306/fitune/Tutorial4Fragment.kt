package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.b306.fitune.databinding.FragmentTutorial4Binding

class Tutorial4Fragment : Fragment() {

    private var _binding: FragmentTutorial4Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTutorial4Binding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = Tutorial4Fragment()
    }
}