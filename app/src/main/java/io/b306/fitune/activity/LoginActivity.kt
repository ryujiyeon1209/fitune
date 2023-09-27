package io.b306.fitune.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityLoginBinding
import io.b306.fitune.room.MyInfoDao
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 버튼 클릭
        binding.liLogin.setOnClickListener {
            // 예시
//            addRecord(MyInfoDao)
        }
    }

    // 일단 급하니까 예시로 insert는 이런 식으로 들어감 - et뭐시기 없음 지금
//    fun addRecord(myInfoDao: MyInfoDao) {
//        val email = binding?.etEmamil?.text.toString()
//        val password = binding?.etPassword?.text.toString()
//
//        if (email.isNotEmpty() && password.isNotEmpty()) {
//            // 코루틴 호출
//            lifecycleScope.launch {
//                myInfoDao.insert(MyInfoEntity(email = email, password = password))
//                // 코루틴스코프 안에 있기 때문에 context는 applicationContext로
//                Toast.makeText(applicationContext, "DB에 저장", Toast.LENGTH_LONG).show()
//                // 패스워드 editText 초기화
//                binding?.etPassword?.text?.clear()
//            }
//        } else {
//            Toast.makeText(applicationContext, "빈 칸이 있음", Toast.LENGTH_LONG).show()
//        }
//    }

}