package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityFightBinding
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5100) // 4000 밀리초 후에 실행
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel() // 액티비티가 종료되면 코루틴도 취소합니다.
    }

}
