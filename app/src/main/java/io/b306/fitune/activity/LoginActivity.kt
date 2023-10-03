package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.LoginRequest
import io.b306.fitune.api.LoginResponse
import io.b306.fitune.api.SuperResponse
import io.b306.fitune.databinding.ActivityLoginBinding
import io.b306.fitune.model.MyInfoData
import io.b306.fitune.room.ExerciseRecordDao
import io.b306.fitune.room.ExerciseRecordEntity
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoDao
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    private lateinit var myInfoDao: MyInfoDao
    private lateinit var exerciseRecordDao: ExerciseRecordDao
    private var myInfoEntity: MyInfoEntity? = null
    private var exerciseRecordEntity: ExerciseRecordEntity? = null

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // db 정보 넣기
        initMyInfoEntity()

        // 회원가입 클릭
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 로그인 버튼 클릭
        binding.liLogin.setOnClickListener {

            var email = binding.etLoginEmail.text.toString();
            var password = binding.etLoginPwd.text.toString();

            val requestBody = LoginRequest(email, password);

            //네트워크 요청을 비동기로 보내기 위한 스코프 정의
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val loginAPI = ApiObject.postRetrofitLoginService
                    val call: Call<LoginResponse> = loginAPI.login(requestBody)
                    val response: Response<LoginResponse> = call.execute()     // Call 객체를 실행하여 Response로 변환


                    //로그인 성공 > 정보를 room에 저장하기!
                    if (response.isSuccessful) {

//                        val myInfoDao = MyApplication.database.myInfoDao()

                        Log.d("로그인되염","")

                        // 로그인 성공 후 두 번째 API 호출: getMyInfo

                        val userAPI = ApiObject.getRetrofitService // UserAPI 인스턴스를 가져옵니다.
                        val myInfoResponse: Response<SuperResponse> = userAPI.getMyInfo(email).execute()

                        if (myInfoResponse.isSuccessful) {
                            val myInfoData = myInfoResponse.body() ?: throw Exception("MyInfoData is null")
                            Log.e("이메일은..",email)
                            Log.e("음냐",myInfoData.toString())

                            // DB에 데이터 저장 로직

                            val userInfo = myInfoData.data.let { it1 ->
                                MyInfoEntity(
                                    userSeq = it1.user!!.userSeq,
                                    email = it1.user.email,
                                    password = it1.user.password,
                                    nickname = it1.user.nickname,
                                    age = it1.user.age,
                                    height = it1.user.height,
                                    weight = it1.user.weight,
                                    bodyFatPercentage = it1.user.bodyFatPercentage,
                                    activeBpm = it1.user.activeBPM,
                                    tension = it1.user.tension,
                                    restingBpm = it1.user.restingBPM,
                                    cellSeq = it1.cell!!.cellSeq,
                                    cellName = it1.cell.cellName,
                                    cellExp = it1.cell.cellExp,
                                    exerciseListSeq = it1.preferExerciseResponse!!.exerciseListSeq,
                                    preferExercise = it1.preferExerciseResponse.exerciseName,
                                )
                            }
                            lifecycleScope.launch(Dispatchers.IO) {
                                if (userInfo != null) {
                                    myInfoDao.insert(userInfo)
                                }
                            }

                            myInfoData.data.exerciseRecord?.forEach { record ->
                                val exerciseRecordEntity = ExerciseRecordEntity(
                                    exerciseRecordSeq = record.exerciseRecordSeq,
                                    exerciseStart = record.exerciseStart,
                                    exerciseEnd = record.exerciseEnd,
                                    exerciseReco = record.exerciseReco,
                                    exerciseAvgBpm = record.exerciseAvgBpm,
                                    exerciseMaxBpm = record.exerciseMaxBpm,
                                    exerciseDistance = record.exerciseDistance,
                                    exerciseReview = record.exerciseReview,
                                    exerciseWeather = record.exerciseWeather
                                )
                                exerciseRecordDao.insert(exerciseRecordEntity)
                            }

                        } else {
                            // getMyInfo API 호출 실패 처리
                            throw Exception("Failed to fetch my info")
                        }


                    } else {
                        showAlert("로그인 실패", "이메일과 비밀번호를 확인해주세요.")
                    }

                    withContext(Dispatchers.Main) { // UI 업데이트는 Main 스레드에서 해야 합니다.
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    // 오류 처리
                } catch (e: Exception) {

                    runOnUiThread {
                        showAlert("로그인 실패", "이메일과 비밀번호를 확인해주세요")
                    }
                }
            }
        }
    }


    //dialogAlert 만들기
    private fun showAlert(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun initMyInfoEntity() {
        // Initialize DAO
        myInfoDao = FituneDatabase.getInstance(this).myInfoDao()
        exerciseRecordDao = FituneDatabase.getInstance(this).exerciseRecordDao()

        // Fetch MyInfoEntity in the background and update UI accordingly
        lifecycleScope.launch(Dispatchers.IO) {
            myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()
            withContext(Dispatchers.Main) {
                updateUI()
            }
        }
    }

    private fun updateUI() {
        // Update UI using myInfoEntity
        Log.d("초기 유저 정보", myInfoEntity.toString())
        if (myInfoEntity?.userSeq!! > 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}