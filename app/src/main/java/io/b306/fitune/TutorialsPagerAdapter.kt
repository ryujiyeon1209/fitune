package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2  // Tutorial1Fragment 하나만 표시하므로 1을 반환합니다.

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tutorial1Fragment()  // 첫 번째 페이지
            1 -> Tutorial2Fragment()  // 두 번째 페이지
//            2 -> Tutorial3Fragment()  // 세 번째 페이지
//            3 -> Tutorial4Fragment()  // 네 번째 페이지
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}