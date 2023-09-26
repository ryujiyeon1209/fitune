package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        val userList = getUserList()

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(this@FightFindFragment, userList) { selectedUser ->
                val dialogFragment = UserDetailDialogFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("user", selectedUser)
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
        fun newInstance() = FightFindFragment()
    }

    private fun getUserList() = arrayListOf(
        UserData("세포3", R.drawable.ic_lv1, 1, 110),
        UserData("세포4", R.drawable.ic_lv2, 2, 115),
        UserData("세포5", R.drawable.ic_lv1, 1, 120),
        UserData("세포6", R.drawable.ic_lv3, 3, 125),
        UserData("세포7", R.drawable.ic_lv0, 0, 130),
        UserData("세포8", R.drawable.ic_lv2, 2, 135),
        UserData("세포9", R.drawable.ic_lv1, 1, 140),
        UserData("세포10", R.drawable.ic_lv3, 3, 145)
    )

}