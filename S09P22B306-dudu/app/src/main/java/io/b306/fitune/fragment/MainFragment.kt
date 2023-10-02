package io.b306.fitune.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.b306.fitune.activity.ManualActivity
import io.b306.fitune.R
import io.b306.fitune.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    // Binding 객체를 nullable로 선언
    private var _binding: FragmentMainBinding? = null
    // Non-Nullable이며, Fragment의 view lifecycle 내에서만 접근 가능
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // layout을 바인딩으로~
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 오늘의 추천 운동 뽑아버리기
        binding.ivFortune.setOnClickListener {
            val recommendDialogFragment = RecommendDialogFragment()
            // supportFragmentManager 대신 childFragmentManager를 사용해야 합니다.
            recommendDialogFragment.show(childFragmentManager, "recommend_dialog")
        }

        // 대결하러가기(대결 상대 추천 list로 이동)
        binding.btnGoFight.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fm_container, FightFindFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 메뉴얼 페이지로 이동(메뉴얼 페이지는 Activity라서 Intent 사용)
        binding.btnManual.setOnClickListener {
            val intent = Intent(activity, ManualActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // View가 Destroy될 때 Binding 객체를 null로 설정해 메모리 누수를 방지합니다.
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}