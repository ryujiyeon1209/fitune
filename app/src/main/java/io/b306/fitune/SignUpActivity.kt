package io.b306.fitune

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
    }
}