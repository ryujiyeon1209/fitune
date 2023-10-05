package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityMainBinding
import io.b306.fitune.fragment.ExerciseHistoryFragment
import io.b306.fitune.fragment.FightResultFragment
import io.b306.fitune.fragment.MainFragment
import io.b306.fitune.fragment.MyPageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 처음 뒤로가기 누르고 시간 재는 용
    private var backPressedTime: Long = 0
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

        // 인텐트에서 데이터 확인
        if (intent.getBooleanExtra("SHOW_FIGHT_RESULT", false)) {
            // FightResultFragment 띄우기
            val fragment = FightResultFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fm_container, fragment)
                .commit()
        }

        // 하단 navbar의 클릭에 따라 보여지는 페이지 달라짐
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_container, MainFragment.newInstance())
                        .commitNow()
                    true
                }

                // 운동 기록 페이지
                R.id.navigation_record -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_container, ExerciseHistoryFragment())
                        .commitNow()
                    true
                }

                // 마이 페이지
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

        // 뒤로가기 버튼
        onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount > 0) {
                // 프래그먼트 백스택에 프래그먼트가 있다면, 이전 프래그먼트로 돌아감
                supportFragmentManager.popBackStack()
            } else {
                // 프래그먼트 백스택에 프래그먼트가 없다면, 뒤로 가기 로직을 실행
                // 현재 시간을 가져오기
                val currentTime = System.currentTimeMillis()

                if (currentTime - backPressedTime <= 2000) {
                    finish()
                } else {
                    // 2초 이상 경과했을 경우 Toast 메시지를 표시하고 뒤로가기 버튼 누른 시간 업데이트
                    backPressedTime = currentTime
                    Toast.makeText(this@MainActivity, "한 번 더 뒤로가기를 누르면 종료합니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    //운동 종료 버튼 누르면 MainActivity로 돌아오기!
    fun onExerciseEndButtonClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}
