package io.b306.fitune.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import io.b306.fitune.R
import io.b306.fitune.adapter.ViewPagerAdapter
import io.b306.fitune.databinding.ActivityManualBinding
import io.b306.fitune.fragment.Manual1Fragment
import io.b306.fitune.fragment.Manual2Fragment
import io.b306.fitune.fragment.Manual3Fragment

class ManualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual)

        binding = ActivityManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val indicator = binding.indicator

        val fragments = arrayListOf<Fragment>(
            Manual1Fragment(),
            Manual2Fragment(),
            Manual3Fragment()
        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter

        // TabLayout 인디케이터로 커스텀 설정
        TabLayoutMediator(indicator, viewPager) { tab, position ->
            // 커스텀 뷰 설정
            val customView = LayoutInflater.from(this).inflate(R.layout.custom_tab_indicator, null)
            val indicatorView = customView.findViewById<View>(R.id.custom_tab_indicator)

            tab.customView = customView

            if (position == 0) {
                indicatorView.setBackgroundResource(R.drawable.tab_indicator_selected)
            }
        }.attach()

        // 페이지 변경 이벤트 처리
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // 페이지가 변경되면 모든 Tab의 인디케이터를 기본 상태로 변경
                for (i in 0 until indicator.tabCount) {
                    val tab = indicator.getTabAt(i)
                    val customView = tab?.customView
                    val indicatorView = customView?.findViewById<View>(R.id.custom_tab_indicator)
                    indicatorView?.setBackgroundResource(R.drawable.tab_indicator_default)
                }

                // 현재 페이지의 인디케이터를 선택된 상태로 변경
                val currentTab = indicator.getTabAt(position)
                val currentCustomView = currentTab?.customView
                val currentIndicatorView = currentCustomView?.findViewById<View>(R.id.custom_tab_indicator)
                currentIndicatorView?.setBackgroundResource(R.drawable.tab_indicator_selected)
            }
        })
    }
}