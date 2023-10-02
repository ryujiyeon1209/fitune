package io.b306.fitune.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.b306.fitune.api.ApiObject
import io.b306.fitune.adapter.UserAdapter
import io.b306.fitune.api.CellResponse
import io.b306.fitune.api.UserResponse
import io.b306.fitune.databinding.FragmentFightFindBinding
import io.b306.fitune.model.BattleUserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FightFindFragment : Fragment() {
    private var userList: List<BattleUserData> = emptyList() // userList를 클래스 멤버 변수로 추가
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

        // Retrofit을 사용하여 API 호출
        val userApi = ApiObject.getRetrofitService
        val userId = 2 // 예시로 userId 값을 설정합니다.

        userApi.getUserList(userId).enqueue(object : Callback<CellResponse> {
            override fun onResponse(call: Call<CellResponse>, response: Response<CellResponse>) {
                if (response.isSuccessful) {
                    val cellResponse = response.body()
                    val cellList = cellResponse?.data

                    Log.e("세포 정보", cellList.toString())

                    if (cellList != null) {
                        val cellDataList = cellResponse.data

                        if (cellDataList != null) {
                            // cellDataList가 null이 아닌 경우에만 처리

                            userList = cellDataList.map { cellData ->
                                BattleUserData(
                                    cellData.userSeq,
                                    cellData.cellExp,
                                    cellData.cellName,
                                    cellData.height,
                                    cellData.weight,
                                    cellData.bpm
                                )
                            }
                            Log.e("어댑터 출력 확인11", userList.toString())
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

                                Log.e("어댑터",adapter.toString())

                            }
                        }
                    } else {
                        // 실패 처리
                    }

                }}

            override fun onFailure(call: Call<CellResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


        Log.e("??",userList.toString())



        userApi.getUserInfo(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val user = userResponse?.data // data에 있는 User 객체를 가져오기

                    // 사용자 정보가 user 변수에 넣기
                    Log.e("유저 정보", user.toString())
                } else {
                    Log.e("response error", "통신 실패")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("network error", "네트워크자체가 안됨ㅠ")
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
//    private fun getUserList() = arrayListOf(
//        UserData("세포3", R.drawable.ic_lv1, 1, 110),
//        UserData("세포4", R.drawable.ic_lv2, 2, 115),
//        UserData("세포5", R.drawable.ic_lv1, 1, 120),
//
//    )

}

