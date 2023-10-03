package io.b306.fitune.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import io.b306.fitune.R
import io.b306.fitune.activity.ExerciseProgressActivity
import io.b306.fitune.activity.ExerciseSelectedActivity
import io.b306.fitune.activity.MainActivity
import io.b306.fitune.databinding.FragmentRecommendDialogBinding

class RecommendDialogFragment : DialogFragment() {

    private var _binding: FragmentRecommendDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentRecommendDialogBinding.inflate(LayoutInflater.from(context))

        // 닫기 버튼 btn_recommend_dialog_close
        binding.btnRecommendDialogClose.setOnClickListener {
            dismiss() // DialogFragment를 닫습니다.
        }
        binding.btnExerciseRecommend1.setOnClickListener {
            val intent = Intent(context, ExerciseSelectedActivity::class.java)
            startActivity(intent)
        }


        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // ViewBinding 참조를 해제합니다.
    }
}