package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import io.b306.fitune.R
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.BattleAddRequest
import io.b306.fitune.databinding.ActivityFightBinding
import io.b306.fitune.fragment.FightFindFragment
import io.b306.fitune.fragment.FightNowResultFragment
import io.b306.fitune.fragment.FightResultDetailDialogFragment
import io.b306.fitune.room.FituneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFightBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extra로 받아온 상대 seq
        val otherUserSeq = intent.getIntExtra("userSeq", 0)

        val myInfoDao = FituneDatabase.getInstance(this).myInfoDao()

        var userSeq: Int? = null
        var userNickname: String? = null

        activityScope.launch {
            userSeq = myInfoDao.getUserSeq()
            userNickname = myInfoDao.getUserNickname()

            Log.e("여긴파이트액티비티 상대시크", otherUserSeq.toString())
            Log.e("여긴파이트액티비티 나시크", userSeq.toString())

            // API 요청
            val battleAddRequest = BattleAddRequest(userSeq!!, otherUserSeq)

            try {
                val response = ApiObject.patchRetrofitBattleService.addBattle(battleAddRequest)

                if (response.isSuccessful) {
                    val responseData = response.body()
                    val dataValue = responseData?.data

                    // 승리/패배 판단
                    val resultMessage = if (userNickname == dataValue) {
                        "승리하였습니다."
                    } else {
                        "패배하였습니다."
                    }

                    // 5100 밀리초 후에 AlertDialog 띄우기
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.ivFighting.setImageResource(0)
                        AlertDialog.Builder(this@FightActivity)
                            .setTitle("결과")
                            .setMessage(resultMessage)
                            .setPositiveButton("확인") { dialog, _ ->
                                dialog.dismiss()

                                moveToMainActivity()
                            }
                            .setCancelable(true) // 확인 말고 바깥 화면 클릭해도 페이지 이동 실행
                            .setOnCancelListener {
                                moveToMainActivity()
                            }
                            .show()
                    }, 5000)

                } else {
                    // TODO: 오류 처리
                    Log.e("API Error", "Response not successful: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // TODO: 예외 처리 (예: 네트워크 연결 오류, 시간 초과 등)
                Log.e("API Exception", e.toString())
            }

        }


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

    private fun moveToMainActivity() {
        val intent = Intent(this@FightActivity, MainActivity::class.java)
        intent.putExtra("SHOW_FIGHT_RESULT", true)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel() // 액티비티가 종료되면 코루틴도 취소합니다.
    }

}
