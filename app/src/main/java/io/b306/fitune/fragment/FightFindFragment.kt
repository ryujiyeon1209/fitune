package io.b306.fitune.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.b306.fitune.api.ApiObject
import io.b306.fitune.R
import io.b306.fitune.adapter.UserAdapter
import io.b306.fitune.model.UserData
import io.b306.fitune.api.UserResponse
import io.b306.fitune.databinding.FragmentFightFindBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // 여기에 retrofit 요청
        // Retrofit을 사용하여 API 호출
        val userApi = ApiObject.getRetrofitService

        val userId = 3 // 예시로 userId 값을 설정합니다.
        userApi.getUserInfo(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val user = userResponse?.data // data에 있는 User 객체를 가져옵니다.

                    // 성공적으로 응답을 받았으며, 사용자 정보가 user 변수에 들어있습니다.
                    // 여기서 원하는 작업을 수행하세요.
                    Log.e("KKKKKKKKKKKKKKKKK", user.toString())
                    Log.e("KKKKKKKKKKKKKKKKK", "PPPPPPPPPPPPPPPPL")
                } else {
                    Log.e("KKKKKKKKKKKKKKKKK", "실패ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("KKKKKKKKKKKKKKKKK", "네트워크자체가 안됨ㅠ")
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FightFindFragment()
    }

    // 얘는 이제 추천 리스트 뽑아주는 거로 대체하면 됨
    private fun getUserList() = arrayListOf(
        UserData("세포3", R.drawable.ic_lv1, 1, 110),
        UserData("세포4", R.drawable.ic_lv2, 2, 115),
        UserData("세포5", R.drawable.ic_lv1, 1, 120),
//        UserData("세포6", R.drawable.ic_lv3, 3, 125),
//        UserData("세포7", R.drawable.ic_lv0, 0, 130),
//        UserData("세포8", R.drawable.ic_lv2, 2, 135),
//        UserData("세포9", R.drawable.ic_lv1, 1, 140),
//        UserData("세포10", R.drawable.ic_lv3, 3, 145)
    )

}