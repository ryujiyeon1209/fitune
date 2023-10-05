package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivitySignUpBinding
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tension 설정하기
        val spinnerTension: Spinner = findViewById(R.id.sp_signUp_tension)

        // Spinner에 표시될 옵션들
        val tensionOptions = arrayOf("고강도", "중강도", "저강도")

        // ArrayAdapter를 사용하여 Spinner와 옵션들을 연결
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tensionOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTension.adapter = adapter


        // 회원가입 버튼 클릭
        binding.btnSignUp.setOnClickListener{
            val email = binding.etSignUpEmail.text.toString()
            val password = binding.etSignUpPwd.text.toString()
            val nickname = binding.etSignUpNickName.text.toString()
            val height = binding.etSignUpHeight.text.toString().toIntOrNull() ?: 0
            val weight = binding.etSignUpWeight.text.toString().toIntOrNull() ?: 0
            val year = binding.etSignUpYaer.text.toString().toIntOrNull() ?: 0
            val tension = when (spinnerTension.selectedItem.toString()) {
                "고강도" -> 3
                "중강도" -> 2
                "저강도" -> 1
                else -> 0 // 기본값 설정을 0으로 해야 값이 제대로 들어감! 1로 설정하면 무조건 1로 들어갑니다
            }
            lifecycleScope.launch(Dispatchers.IO) {
                val myInfoDao = FituneDatabase.getInstance(this@SignUpActivity).myInfoDao()
                var myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()
                if (myInfoEntity != null) {
                    // Update existing entity
                    myInfoEntity.email = email
                    myInfoEntity.password = password
                    myInfoEntity.nickname = nickname
                    myInfoEntity.height=height
                    myInfoEntity.weight=weight
                    myInfoEntity.year= year
                    myInfoEntity.tension = tension

                    myInfoDao.insert(myInfoEntity)
                    Log.d("초기 유저 정보",myInfoEntity.toString())

                }
                val intent = Intent(this@SignUpActivity, TutorialsActivity::class.java)
                startActivity(intent)
                finish()

            }

            // SignUpActivity 종료
        }
    }
}