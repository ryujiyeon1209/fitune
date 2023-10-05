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
import io.b306.fitune.room.FituneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FightFindFragment : Fragment() {
    private var userList: List<BattleUserData> = emptyList() // userList를 클래스 멤버 변수로 추가
    private var _binding: FragmentFightFindBinding? = null
    private val binding get() = _binding!!

    // Fragment 내부의 멤버 변수로 코루틴 스코프 정의
    private val viewModelScope = CoroutineScope(Dispatchers.Main + Job())

    // userSeq
    private var userId : Int = 0

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
            ): View? {
            _binding = FragmentFightFindBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.tvFightFindLoading.visibility = View.VISIBLE // 통신 시작 전에 보이게 설정

            // 데이타베입스 연결
            val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()

            // Retrofit을 사용하여 API 호출
            val userApi = ApiObject.getRetrofitService

            //room에서 가져온 내 아이디
            viewModelScope.launch {
                userId = myInfoDao.getUserSeq()

                userApi.getUserList(userId).enqueue(object : Callback<CellResponse> {
                    override fun onResponse(
                        call: Call<CellResponse>,
                        response: Response<CellResponse>
                    ) {
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
                                        adapter = UserAdapter(
                                            this@FightFindFragment,
                                            userList
                                        ) { selectedUser ->
                                            val dialogFragment = UserDetailDialogFragment().apply {
                                                arguments = Bundle().apply {
                                                    putParcelable("user", selectedUser)
                                                }
                                            }
                                            dialogFragment.show(parentFragmentManager, "userDetail")
                                        }

                                        Log.e("어댑터", adapter.toString())

                                    }
                                }
                            } else {
                                // 실패 처리
                            }

                        }
                        binding.tvFightFindLoading.visibility = View.GONE // 데이터 로딩 후에 숨기기
                    }

                    override fun onFailure(call: Call<CellResponse>, t: Throwable) {
                        binding.tvFightFindLoading.visibility = View.GONE // 통신 실패했을 때도 숨기기
                    }
                })
            }


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


}

