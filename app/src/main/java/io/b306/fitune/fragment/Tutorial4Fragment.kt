package io.b306.fitune.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.databinding.FragmentTutorial4Binding

class Tutorial4Fragment : Fragment() {

    private var _binding: FragmentTutorial4Binding? = null
    private val binding get() = _binding!!

    interface TutorialPageNavigator {
        fun moveToNextPage()
    }

    private var pageNavigator: TutorialPageNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TutorialPageNavigator) {
            pageNavigator = context
        } else {
            throw RuntimeException("$context must implement TutorialPageNavigator")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTutorial4.setOnClickListener{
            // 버튼이 클릭되면 다음 페이지로 이동
            pageNavigator?.moveToNextPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 바인딩 참조를 정리합니다.
        _binding = null
    }
    companion object {
        fun newInstance() = Tutorial4Fragment()
    }
}