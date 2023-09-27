package io.b306.fitune.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityMainBinding
import io.b306.fitune.fragment.ExerciseHistoryFragment
import io.b306.fitune.fragment.MainFragment
import io.b306.fitune.fragment.MyPageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 화면은 MainFragment로
        supportFragmentManager.beginTransaction()
            .replace(R.id.fm_container, MainFragment.newInstance())
            .commitNow()

        // 처음에 navbar의 home 버튼을 default 값으로 클릭된 상태 설정
        binding.navigation.selectedItemId = R.id.navigation_home

        // 하단 navbar의 클릭에 따라 보여지는 페이지 달라짐
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_container, MainFragment.newInstance())
                        .commitNow()
                    true
                }

                R.id.navigation_record -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_container, ExerciseHistoryFragment())
                        .commitNow()
                    true
                }
                R.id.navigation_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_container, MyPageFragment())
                        .commitNow()
                    true
                }
                // else 없어도 됨 - 다른 이벤트가 없어서
                else -> false
            }
        }


    }


}
