package io.b306.fitune.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import io.b306.fitune.model.FightResultData
import io.b306.fitune.databinding.FragmentFightResultDetailDialogBinding
import io.b306.fitune.model.FightRecordData

class FightResultDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentFightResultDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentFightResultDetailDialogBinding.inflate(LayoutInflater.from(context))


        // SDK 33이상이면 새로운 getParcelable 메소드를 사용
        val fightResult: FightRecordData? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("fight_result", FightRecordData::class.java)
        } else {
            arguments?.getParcelable("fight_result")
        }

        fightResult?.let {
            binding.tvDetailFightDate.text = it.battleDate
            binding.tvDetailFightUserName.text = "상대 닉네임 : ${it.battleOtherCellName}"
            binding.tvDetailFightResult.text = if (it.battleOtherName == it.winnerName) {
                "패배"
            } else {
                "승리"
            }

        }

        binding.btnFightResultClose.setOnClickListener {
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