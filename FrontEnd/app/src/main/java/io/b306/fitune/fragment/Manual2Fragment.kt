package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.databinding.FragmentManual1Binding
import io.b306.fitune.databinding.FragmentManual2Binding

class Manual2Fragment : Fragment() {

    private var _binding: FragmentManual2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManual2Binding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = Manual2Fragment()
    }
}