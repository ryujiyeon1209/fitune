package io.b306.fitune.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityExerciseProgressBinding
import io.b306.fitune.databinding.ActivityExerciseSelectedBinding

class ExerciseSelectedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseSelectedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_selected)
        binding = ActivityExerciseSelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnExerciseStart.setOnClickListener {
            //ExerciseProgressActivity로 이동, 현재시간 주기!
            val intent = Intent(this@ExerciseSelectedActivity, ExerciseProgressActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

}