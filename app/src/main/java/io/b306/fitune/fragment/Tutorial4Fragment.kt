package io.b306.fitune.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.R
import io.b306.fitune.activity.MainActivity
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.signUpResponse
import io.b306.fitune.databinding.FragmentTutorial4Binding
import io.b306.fitune.model.SignUpUser
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class Tutorial4Fragment : Fragment() {

    private var _binding: FragmentTutorial4Binding? = null
    private val binding get() = _binding!!

    interface TutorialPageNavigator {
        fun moveToNextPage()
    }

    private var pageNavigator: TutorialPageNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TutorialPageNavigator) {
            pageNavigator = context
        } else {
            throw RuntimeException("$context must implement TutorialPageNavigator")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnTutorial4Start = view.findViewById<Button>(R.id.btn_tutorial4)

        btnTutorial4Start.setOnClickListener{
            // 버튼이 클릭되면 다음 페이지로 이동
            lifecycleScope.launch(Dispatchers.IO) {
                val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
                val myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()

                val enteredName = binding.editText.text.toString()
                if (enteredName.isNotBlank()) {
                    myInfoEntity.cellName = enteredName
                }

                if (myInfoEntity.id == 1) {
                    myInfoDao.update(myInfoEntity)
                    Log.d("업데이트 유저",myInfoEntity.toString())
                } else {
                    myInfoDao.insert(myInfoEntity)
                    Log.d("유저 넣었습니다.",myInfoEntity.toString())
                }
            }

            //회원가입 API 보내기


            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val userData = getUserDataFromRoom()
                    val signUpAPI = ApiObject.postRetrofitSignUpService
                    val call: Call<signUpResponse>? = signUpAPI.createUser(userData)
                    val response: Response<signUpResponse>? = call?.execute()

                    if (response != null && response.isSuccessful) {

                    } else {
                        // 서버에서 실패 응답을 받았을 때 처리
                    }
                } catch (e: Exception) {
                    // 네트워크 오류 또는 예외 발생 시 처리
                }
            }
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 바인딩 참조를 정리합니다.
        _binding = null
    }
    companion object {
        fun newInstance() = Tutorial4Fragment()
    }

    // 사용자 데이터를 가져오는 함수
    private suspend fun getUserDataFromRoom(): SignUpUser? {
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val myInfoEntity =myInfoDao.getMyInfo() ?: MyInfoEntity()

        // myInfoEntity에서 필요한 데이터 추출
        return myInfoEntity?.let {
            SignUpUser(
                email = it.email,
                password = it.password,
                nickName = it.nickname,
                height = it.height,
                weight = it.weight,
                age = it.age,
                restingBpm = it.restingBpm,
                activeBpm = it.activeBpm,
                bodyFatPer = 0,
                preferExerciseSeq = it.exerciseListSeq,
                cellName = it.cellName
            )
        }
    }

}