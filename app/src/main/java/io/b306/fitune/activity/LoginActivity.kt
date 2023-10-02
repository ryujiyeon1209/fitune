package io.b306.fitune.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.R
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.LoginAPI
import io.b306.fitune.api.LoginRequest
import io.b306.fitune.api.LoginResponse
import io.b306.fitune.databinding.ActivityLoginBinding
import io.b306.fitune.room.MyInfoDao
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 버튼 클릭
        binding.liLogin.setOnClickListener {

            var email = binding.emailEditText.text.toString();
            var password = binding.pwdEditText.text.toString();

            val requestBody = LoginRequest(email, password);

            //네트워크 요청을 비동기로 보내기 위한 스코프 정의
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val loginAPI = ApiObject.postRetrofitLoginService
                    val call: Call<LoginResponse> = loginAPI.login(requestBody)
                    val response: Response<LoginResponse> = call.execute()     // Call 객체를 실행하여 Response로 변환

                    //로그인 성공 > 정보를 room에 저장하기!
                    if (response.message().equals("SUCCESS")) {
                        // 새로운 API 호출
//                        val superEmailAPI = ApiObject.getRetrofitService.getSuperEmail()
//                        val superEmailResponse: Response<YourResponseModel> = superEmailAPI.execute()
//
//                        //room에 저장하기
//                        if (superEmailResponse.isSuccessful) {
//
//                        }

                    }

                    // 오류 처리
                } catch (e: Exception) {
                    runOnUiThread {
                        showAlert("로그인 실패", "이메일과 비밀번호를 확인해주세요.")
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



}