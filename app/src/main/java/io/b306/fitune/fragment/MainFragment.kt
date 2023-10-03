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
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoRepository
import io.b306.fitune.viewmodel.MyInfoViewModel
import io.b306.fitune.viewmodel.MyInfoViewModelFactory

class MainFragment : Fragment() {

    // Binding 객체를 nullable로 선언
    private var _binding: FragmentMainBinding? = null
    // Non-Nullable이며, Fragment의 view lifecycle 내에서만 접근 가능
    private val binding get() = _binding!!

    // ViewModel과 Repository 인스턴스
    private lateinit var viewModel: MyInfoViewModel
    private lateinit var viewModelFactory: MyInfoViewModelFactory

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

        // Repository와 ViewModelFactory 인스턴스 생성
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val repository = MyInfoRepository(myInfoDao)
        viewModelFactory = MyInfoViewModelFactory(repository)

        // ViewModel 인스턴스 가져오기
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyInfoViewModel::class.java)

        // ViewModel의 LiveData 옵저빙
        viewModel.myInfo.observe(viewLifecycleOwner, { myInfo ->
            // 여기서 UI 업데이트 로직이 수행됩니다. 예를 들어:
            // binding.textView.text = myInfo?.nickname ?: "No nickname available"
//            pb_main_exp
            var cellLv = ((myInfo?.cellExp?.div(10000))?.plus(1)) ?: 3
            var maxExp = ((myInfo?.cellExp?.div(10000))?.plus(1))?.times(
                10000
            ) ?: 0
            var exp = myInfo?.cellExp ?: 10000
            binding.tvMainCellName.text = myInfo?.cellName ?: "세포 이름"
            binding.tvMainCellLv.text = ((myInfo?.cellExp?.div(10000))?.plus(1)).toString()
            binding.tvMainCellExp.text = "${exp} / ${maxExp}"
            // 아래는 progressbar
            if (maxExp != null) {
                binding.pbMainExp.max = maxExp
            }
            if (exp != null) {
                binding.pbMainExp.progress = exp
            }
            // 이미지 변경 로직
            val imageResId = when(cellLv) {
                1 -> R.drawable.ic_lv1
                2 -> R.drawable.ic_lv2
                3 -> R.drawable.ic_lv3
                else -> R.drawable.ic_lv0 // 기본 이미지
            }
            binding.ivMainCell.setImageResource(imageResId)
        })

        // UserInfo 가져오기
        viewModel.fetchMyInfo()

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