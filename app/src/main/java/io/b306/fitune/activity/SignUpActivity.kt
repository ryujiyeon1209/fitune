package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
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

        // 회원가입 버튼 클릭
        binding.btnSignUp.setOnClickListener{
            val email = binding.etSignUpEmail.text.toString()
            val nickname = binding.etSignUpNickName.text.toString()
            val height = binding.etSignUpHeight.text.toString().toIntOrNull() ?: 0
            val weight = binding.etSignUpWeight.text.toString().toIntOrNull() ?: 0
            val age = binding.etSignUpAge.text.toString().toIntOrNull() ?: 0
            lifecycleScope.launch(Dispatchers.IO) {
                val myInfoDao = FituneDatabase.getInstance(this@SignUpActivity).myInfoDao()
                var myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()
                if (myInfoEntity != null) {
                    // Update existing entity
                    myInfoEntity.email = email
                    myInfoEntity.nickname = nickname
                    myInfoEntity.height=height
                    myInfoEntity.weight=weight
                    myInfoEntity.age= age
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