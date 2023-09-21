package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import io.b306.fitune.databinding.ActivityMainBinding

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
                        .replace(R.id.fm_container, MypageFragment())
                        .commitNow()
                    true
                }
                // else 없어도 됨 - 다른 이벤트가 없어서
                else -> false
            }
        }
    }
}
