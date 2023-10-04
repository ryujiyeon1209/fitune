package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityFightBinding
import io.b306.fitune.fragment.FightFindFragment
import io.b306.fitune.fragment.FightNowResultFragment
import io.b306.fitune.fragment.FightResultDetailDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class FightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFightBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userSeq = intent.getStringExtra("userSeq")

        binding.tvUserSeq.text = userSeq

        Glide.with(this)
            .asGif()
            .load(R.drawable.gif_fighting)
            .into(binding.ivFighting)

        Handler(Looper.getMainLooper()).postDelayed({
            // 현재 액티비티를 종료
            finish()


            // FightFindFragment로 이동
            val findFragment = FightFindFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, findFragment)
                .commit()

            // FightNowResultFragment를 띄우는 코드 추가
            val nowResultFragment = FightNowResultFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, nowResultFragment)
                .addToBackStack(null) // 백 스택에 추가 (선택 사항)
                .commit()
        }, 5100) // 4000 밀리초 후에 실행

    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel() // 액티비티가 종료되면 코루틴도 취소합니다.
    }

}
