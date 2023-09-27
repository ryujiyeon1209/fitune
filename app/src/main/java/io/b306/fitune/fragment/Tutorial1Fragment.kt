package io.b306.fitune.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import io.b306.fitune.R
import io.b306.fitune.activity.TutorialsActivity
import io.b306.fitune.databinding.FragmentTutorial1Binding
import kotlin.math.log

class Tutorial1Fragment : Fragment() {

    private var _binding: FragmentTutorial1Binding? = null
    private val binding get() = _binding!!
    private lateinit var heartRateTextView: TextView
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
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial1Binding.inflate(inflater, container, false)
        heartRateTextView = binding.heartRateTextView

        val tutorialsActivity = activity as TutorialsActivity

        tutorialsActivity.nowHeartRate.observe(viewLifecycleOwner) { hearRate ->
            heartRateTextView.text = "$hearRate bpm"
        }

        Glide.with(this).load(R.drawable.ic_heartbeat).into(binding.ivTutorialHeart)

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
        Log.e("fragment destroy", "destroy view")
        // 뷰가 파괴될 때 바인딩 참조를 정리합니다.
        _binding = null


    }
    companion object {
        fun newInstance() = Tutorial1Fragment()
    }
}
