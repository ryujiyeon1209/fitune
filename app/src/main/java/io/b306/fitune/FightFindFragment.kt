package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.databinding.FragmentFightFindBinding

class FightFindFragment : Fragment() {

    private var _binding: FragmentFightFindBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFightFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userList = arrayListOf(
            UserData("세포1", R.drawable.ic_check_mark),
            UserData("세포2", R.drawable.ic_check_mark)
            // 다른 사용자 데이터도 추가 가능
        )

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            adapter = context?.let { UserAdapter(it, userList) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FightFindFragment()
    }

}