package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivFortune = findViewById<ImageView>(R.id.iv_fortune)
        ivFortune.setOnClickListener {
            val recommendDialogFragment = RecommendDialogFragment()
            recommendDialogFragment.show(supportFragmentManager, "recommend_dialog")
        }
    }
}
