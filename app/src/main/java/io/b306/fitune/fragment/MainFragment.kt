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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoDao
import io.b306.fitune.room.MyInfoEntity
import io.b306.fitune.room.MyInfoRepository
import io.b306.fitune.viewmodel.MyInfoViewModel
import io.b306.fitune.viewmodel.MyInfoViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
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

    // ViewModel과 Repository 인스턴스
    private lateinit var viewModel: MyInfoViewModel
    private lateinit var viewModelFactory: MyInfoViewModelFactory
    private lateinit var myInfoDao: MyInfoDao
    private lateinit var myInfoEntity: MyInfoEntity
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

        // Repository와 ViewModelFactory 인스턴스 생성
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val repository = MyInfoRepository(myInfoDao)
        viewModelFactory = MyInfoViewModelFactory(repository)

        // ViewModel 인스턴스 가져오기
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyInfoViewModel::class.java)

        // ViewModel의 LiveData 옵저빙
        viewModel.myInfo.observe(viewLifecycleOwner) { myInfo ->
            // UI 업데이트 로직
            var cellLv = ((myInfo?.cellExp?.div(10000))?.plus(1)) ?: 3
            var maxExp = ((myInfo?.cellExp?.div(10000))?.plus(1))?.times(
                10000
            ) ?: 0

            var exp = myInfo?.cellExp ?: 10000
            binding.tvMainCellName.text = myInfo?.cellName ?: "세포 이름"
            binding.tvMainCellLv.text = ((myInfo?.cellExp?.div(10000))?.plus(1)).toString()
            binding.tvMainCellExp.text = "${exp} / ${maxExp}"
            // 아래는 progressbar
            if (maxExp != null) {
                binding.pbMainExp.max = maxExp
            }
            if (exp != null) {
                binding.pbMainExp.progress = exp
            }
            // 이미지 변경 로직
            val imageResId = when (cellLv) {
                1 -> R.drawable.ic_lv1
                2 -> R.drawable.ic_lv2
                3 -> R.drawable.ic_lv3
                else -> R.drawable.ic_lv0 // 기본 이미지
            }
            binding.ivMainCell.setImageResource(imageResId)
        }

        // UserInfo 가져오기
        viewModel.fetchMyInfo()

        // 오늘의 추천 운동 API 요청
        binding.ivFortune.setOnClickListener {
            val recommendDialogFragment = RecommendDialogFragment()

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val userData = getUserDataFromRoom()

                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://j9b306.p.ssafy.io:5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val recommendAPI = retrofit.create(RecommendAPI::class.java)
                    val call: Call<RecommendResponse>? = recommendAPI.recommendExercise(userData)
                    val response: Response<RecommendResponse>? = call?.execute()

                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("운동추천 API 성공", "성공이다아ㅏ!")
                            Log.d("운동 추천 데이터", myInfoEntity.toString())
                            var exerciseCount = FituneDatabase.getInstance(requireContext()).exerciseRecordDao().fetchAllExerciseRecord()?.first()?.size ?: 0
                            Log.d("운동갯수", exerciseCount.toString())

                            // room에 저장하기!

                            val recommendResponse = response.body()?.data // API 응답 데이터
                            myInfoEntity.recommendExercise1 = recommendResponse?.recommendFirst.toString()
                            myInfoEntity.recommendExercise2 = recommendResponse?.recommendSecond.toString()
                            myInfoEntity.recommendExercise3 = recommendResponse?.recommendThird.toString()
                            myInfoEntity.targetBpm = ((0.5+(0.1)*myInfoEntity.tension)*(220-myInfoEntity.age-myInfoEntity.restingBpm)+myInfoEntity.restingBpm).toInt()
                            myInfoEntity.targetTime = 60-15*myInfoEntity.tension - 3/exerciseCount
                            myInfoDao.update(myInfoEntity)
                            // 예시: API 응답 데이터를 로그로 출력
                            Log.d("운동 추천 데이터", myInfoEntity.toString())

                        } else {
                            Log.d("운동추천 API 실패", "실패...ㅠ")
                        }
                    }
                } catch (e: Exception) {

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
        myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        myInfoEntity = myInfoDao.getMyInfo()!!
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