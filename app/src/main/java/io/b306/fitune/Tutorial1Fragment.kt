package io.b306.fitune

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.b306.fitune.databinding.FragmentTutorial1Binding

class Tutorial1Fragment : Fragment() {

    private var _binding: FragmentTutorial1Binding? = null
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
        _binding = FragmentTutorial1Binding.inflate(inflater, container, false)

        // 여기서 ViewBinding 객체를 사용하여 View에 접근할 수 있습니다.
        // 예를 들어, ImageView에 GIF 이미지를 로드합니다.
        Glide.with(this).load(R.drawable.ic_heartbeat).into(binding.ivTutorialHeart)

        // 바인딩 된 뷰를 반환합니다.
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTutorial1.setOnClickListener{
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
        fun newInstance() = Tutorial1Fragment()
    }
}