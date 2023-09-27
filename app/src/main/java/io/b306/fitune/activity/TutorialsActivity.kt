package io.b306.fitune.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.b306.fitune.adapter.TutorialsPagerAdapter
import io.b306.fitune.databinding.ActivityTutorialsBinding
import io.b306.fitune.fragment.Tutorial1Fragment
import io.b306.fitune.fragment.Tutorial2Fragment
import io.b306.fitune.fragment.Tutorial3Fragment
import io.b306.fitune.fragment.Tutorial4Fragment

class TutorialsActivity : AppCompatActivity(),
    Tutorial1Fragment.TutorialPageNavigator, Tutorial2Fragment.TutorialPageNavigator,
    Tutorial3Fragment.TutorialPageNavigator, Tutorial4Fragment.TutorialPageNavigator {

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