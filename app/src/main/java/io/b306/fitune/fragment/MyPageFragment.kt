package io.b306.fitune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.R
import io.b306.fitune.databinding.FragmentMypageBinding
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoRepository
import io.b306.fitune.viewmodel.MyInfoViewModel
import io.b306.fitune.viewmodel.MyInfoViewModelFactory

class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    // ViewModel과 Repository 인스턴스
    private lateinit var viewModel: MyInfoViewModel
    private lateinit var viewModelFactory: MyInfoViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Repository와 ViewModelFactory 인스턴스 생성
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val repository = MyInfoRepository(myInfoDao)
        viewModelFactory = MyInfoViewModelFactory(repository)

        // ViewModel 인스턴스 가져오기
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyInfoViewModel::class.java)

        // ViewModel의 LiveData 옵저빙
        viewModel.myInfo.observe(viewLifecycleOwner) { myInfo ->
            // UI 업데이트 로직
            var cellLv = ((myInfo?.cellExp?.div(10000))?.plus(1)) ?: 3
            var exp = myInfo?.cellExp ?: 10000
            binding.tvMypageNickname.text = myInfo?.nickname ?: "사용자"
            binding.tvMypageLv.text = cellLv.toString()
            binding.tvMypageCellExp.text = "${exp} exp"
            // 이미지 변경 로직
            val imageResId = when (cellLv) {
                1 -> R.drawable.ic_lv1
                2 -> R.drawable.ic_lv2
                3 -> R.drawable.ic_lv3
                else -> R.drawable.ic_lv0 // 기본 이미지
            }
            binding.ivMypageCell.setImageResource(imageResId)
        }

        // UserInfo 가져오기
        viewModel.fetchMyInfo()

        // 내 정보 관리
        binding.liMyInfo.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fm_container, MyEditFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        // 내 대결 기록
        binding.liMyFight.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fm_container, FightResultFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수를 방지하기 위해 binding 객체를 null로 설정
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }
}