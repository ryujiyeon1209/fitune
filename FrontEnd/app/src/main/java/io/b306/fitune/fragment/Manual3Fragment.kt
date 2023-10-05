package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.databinding.FragmentManual3Binding

class Manual3Fragment : Fragment() {

    private var _binding: FragmentManual3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManual3Binding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = Manual3Fragment()
    }
}