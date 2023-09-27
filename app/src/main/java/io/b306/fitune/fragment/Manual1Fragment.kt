package io.b306.fitune.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.b306.fitune.databinding.FragmentManual1Binding

class Manual1Fragment : Fragment() {

    private var _binding: FragmentManual1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManual1Binding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = Manual1Fragment()
    }
}
