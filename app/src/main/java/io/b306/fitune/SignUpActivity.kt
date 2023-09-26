package io.b306.fitune

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.b306.fitune.databinding.ActivityMainBinding
import io.b306.fitune.databinding.ActivitySignUpBinding
import io.b306.fitune.databinding.FragmentMainBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭
        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // SignUpActivity 종료
        }
    }
}