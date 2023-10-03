package io.b306.fitune.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import io.b306.fitune.activity.ManualActivity
import io.b306.fitune.R
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.RecommendAPI
import io.b306.fitune.api.RecommendResponse
import io.b306.fitune.api.RecommendUser
import io.b306.fitune.databinding.FragmentMainBinding
import io.b306.fitune.room.FituneDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    // Binding 객체를 nullable로 선언
    private var _binding: FragmentMainBinding? = null
    // Non-Nullable이며, Fragment의 view lifecycle 내에서만 접근 가능
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // layout을 바인딩으로~
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 오늘의 추천 운동 뽑아버리기
        binding.ivFortune.setOnClickListener {
            val recommendDialogFragment = RecommendDialogFragment()
            // supportFragmentManager 대신 childFragmentManager를 사용해야 합니다.
            Log.d("123","213321321")
            //운동 추천 API
            //운동 추천 API 호출
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val userData = getUserDataFromRoom()

                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://j9b306.p.ssafy.io:5000/") // 백엔드 API의 기본 URL을 여기에 입력
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val recommendAPI = retrofit.create(RecommendAPI::class.java)
                    val call: Call<RecommendResponse>? = recommendAPI.recommendExercise(userData)
                    val response: Response<RecommendResponse>? = call?.execute()

                    if (response != null) {
                        Log.d(" API result", "널을 아니야!!")
                        Log.d(" API result", response.toString())
                        if (response.isSuccessful) {
                            Log.d("운동추천 API 성공", "성공이다아ㅏ!")

                            // 여기에서 API 응답 데이터를 화면에 표시하는 로직을 추가하세요.
                            val recommendResponse = response.body() // API 응답 데이터

                            // 예시: API 응답 데이터를 로그로 출력
                            Log.d("운동 추천 데이터", recommendResponse.toString())
                        } else {
                            Log.d("운동추천 API 실패", "실패...ㅠ")
                        }
                    }
                } catch (e: Exception) {
                    // 네트워크 오류 또는 예외 발생 시 처리
                }
            }

            recommendDialogFragment.show(childFragmentManager, "recommend_dialog")
        }

        // 대결하러가기(대결 상대 추천 list로 이동)
        binding.btnGoFight.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fm_container, FightFindFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 메뉴얼 페이지로 이동(메뉴얼 페이지는 Activity라서 Intent 사용)
        binding.btnManual.setOnClickListener {
            val intent = Intent(activity, ManualActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // View가 Destroy될 때 Binding 객체를 null로 설정해 메모리 누수를 방지합니다.
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    // 사용자 데이터를 가져오는 함수
    private suspend fun getUserDataFromRoom(): RecommendUser? {
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val myInfoEntity = myInfoDao.getMyInfo()


        // myInfoEntity에서 필요한 데이터 추출
        return myInfoEntity?.let {
            RecommendUser(
                userSeq = 1,
                age = it.age,
                height = it.height,
                weight = it.weight,
                body_fat_percentage = it.bodyFatPercentage,
                active_BPM = it.activeBpm,
                resting_BPM = it.restingBpm
            )
        }
    }

}