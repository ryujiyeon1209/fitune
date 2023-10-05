package io.b306.fitune.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import io.b306.fitune.activity.ExerciseProgressActivity
import io.b306.fitune.databinding.FragmentRecommendDialogBinding
import io.b306.fitune.model.getImageResourceByExerciseName
import io.b306.fitune.model.getKoreanNAmeByExerciseName
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

        // 너비 설정
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.7).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val exerciseRecommendDao = FituneDatabase.getInstance(requireContext()).exerciseRecommendDao()
        val repository = ExerciseRecommendRepository(exerciseRecommendDao)
        val viewModelFactory = ExerciseRecommendViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseRecommendViewModel::class.java)

        val userId = 1

        // LiveData를 사용하여 데이터를 비동기적으로 관찰하고 UI 업데이트
        viewModel.myInfo.observe(viewLifecycleOwner, Observer { myInfo ->
            if (myInfo != null) {
                //운동 종목
                binding.tvRecommendType1.setText(getKoreanNAmeByExerciseName(myInfo.recommendExercise1))
                binding.tvRecommendType2.setText(getKoreanNAmeByExerciseName(myInfo.recommendExercise2))
                binding.tvRecommendType3.setText(getKoreanNAmeByExerciseName(myInfo.recommendExercise3))

                //운동 이미지
                binding.ivRecommend1.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise1))
                binding.ivRecommend2.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise2))
                binding.ivRecommend3.setImageResource(getImageResourceByExerciseName(myInfo.recommendExercise3))

                //목표
                binding.tvRecommendTargetTime.text = myInfo.targetTime.toString() + "분"
                binding.tvRecommendTargetHeart.text = myInfo.targetBpm.toString() + "BPM"
            }
        })

        binding.btnRecommendDialogClose.setOnClickListener {
            dismiss()
        }

        //운동 선택 버튼을 누르면 ExerciseProgressActivity로 화면 전환
        binding.btnExerciseRecommend1.setOnClickListener {
            val intent = Intent(context, ExerciseProgressActivity::class.java)
            startActivity(intent)

            dismiss()
        }

        binding.btnExerciseRecommend2.setOnClickListener {
            val intent = Intent(context, ExerciseProgressActivity::class.java)
            startActivity(intent)

            dismiss()
        }

        binding.btnExerciseRecommend3.setOnClickListener {
            val intent = Intent(context, ExerciseProgressActivity::class.java)
            startActivity(intent)

            dismiss()
        }


        // 데이터를 가져오는 작업 시작
        viewModel.fetchMyInfo(userId)

        return binding.root
    }
}
