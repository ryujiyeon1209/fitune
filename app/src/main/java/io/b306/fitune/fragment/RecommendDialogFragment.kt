package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import io.b306.fitune.databinding.FragmentRecommendDialogBinding
import io.b306.fitune.model.getImageResourceByExerciseName
import io.b306.fitune.room.ExerciseRecommendRepository
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.viewmodel.ExerciseRecommendViewModel
import io.b306.fitune.viewmodel.ExerciseRecommendViewModelFactory

class RecommendDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentRecommendDialogBinding
    private lateinit var viewModel: ExerciseRecommendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendDialogBinding.inflate(inflater, container, false)

        val exerciseRecommendDao = FituneDatabase.getInstance(requireContext()).exerciseRecommendDao()
        val repository = ExerciseRecommendRepository(exerciseRecommendDao)
        val viewModelFactory = ExerciseRecommendViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseRecommendViewModel::class.java)

        val userId = 1

        // LiveData를 사용하여 데이터를 비동기적으로 관찰하고 UI 업데이트
        viewModel.myInfo.observe(viewLifecycleOwner, Observer { myInfo ->
            if (myInfo != null) {
                binding.tvRecommendType1.text = myInfo.recommendExercise1
                binding.tvRecommendType2.text = myInfo.recommendExercise2
                binding.tvRecommendType3.text = myInfo.recommendExercise3

                binding.ivRecommend1.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise1))
                binding.ivRecommend2.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise2))
                binding.ivRecommend3.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise3))

                binding.tvRecommendTargetTime.text = myInfo.targetTime.toString() + "분"
                binding.tvRecommendTargetHeart.text = myInfo.targetBpm.toString() + "BPM"
            }
        })

        binding.btnRecommendDialogClose.setOnClickListener {
            dismiss()
        }

        // 데이터를 가져오는 작업 시작
        viewModel.fetchMyInfo(userId)

        return binding.root
    }
}
