package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.b306.fitune.databinding.ActivityTutorialsBinding

class TutorialsActivity : AppCompatActivity(), Tutorial1Fragment.TutorialPageNavigator, Tutorial2Fragment.TutorialPageNavigator  {

    private lateinit var binding: ActivityTutorialsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewPager2에 어댑터를 설정합니다.
        val adapter = TutorialsPagerAdapter(this)
        binding.viewPagerTutorials.adapter = adapter
    }

    override fun moveToNextPage() {
        val currentPosition = binding.viewPagerTutorials.currentItem
        val nextPosition = currentPosition + 1
        binding.viewPagerTutorials.setCurrentItem(nextPosition, true)
    }
}