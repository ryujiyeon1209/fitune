package io.b306.fitune.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import io.b306.fitune.model.BattleUserData
import io.b306.fitune.databinding.FragmentUserDetailDialogBinding

class UserDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentUserDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentUserDetailDialogBinding.inflate(LayoutInflater.from(context))


        // SDK 33이상이면 새로운 getParcelable 메소드를 사용
        val user: BattleUserData? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("user", BattleUserData::class.java)
        } else {
            arguments?.getParcelable("user")
        }

        user?.let {
            binding.tvClickUserName.text = it.cellName
//            binding.ivUserProfile.setImageResource(it.userImageResource)
            binding.tvUserHeight.text = "${it.height}cm"
            binding.tvUserWeight.text = "${it.weight}kg"
            binding.tvUserBpm.text = "${it.bpm}bpm"
        }

        binding.btnUserDetailDialogClose.setOnClickListener{
            dismiss() // DialogFragment 닫기
        }

        return activity?.let {
            AlertDialog.Builder(it)
                .setView(binding.root)
                .create()
        } ?: throw IllegalStateException("Activity가 null")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}