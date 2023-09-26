package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.b306.fitune.databinding.FragmentFightResultBinding

class FightResultFragment : Fragment() {

    private var _binding: FragmentFightResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFightResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fightList = getFightList()

        binding.rvFightResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FightResultAdapter(this@FightResultFragment, fightList) { selectedUser ->
                val dialogFragment = FightResultDetailDialogFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("fight_result", selectedUser)
                    }
                }
                dialogFragment.show(parentFragmentManager, "userDetail")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FightResultFragment()
    }

    private fun getFightList() = arrayListOf(
        FightResultData("2023-09-02", "세포3", "승리"),
        FightResultData("2023-09-05", "세포4", "패배"),
        FightResultData("2023-09-06", "세포5", "승리"),
        FightResultData("2023-09-10", "세포6", "승리"),
        FightResultData("2023-09-12", "세포7", "패배"),
        FightResultData("2023-09-17", "세포8", "승리"),
        FightResultData("2023-09-18", "세포9", "패배"),
        FightResultData("2023-09-24", "세포10", "패배")
    )
}