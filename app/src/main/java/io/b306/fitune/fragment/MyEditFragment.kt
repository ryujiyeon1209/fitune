package io.b306.fitune.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.R
import io.b306.fitune.api.ApiObject
import io.b306.fitune.databinding.FragmentMyEditBinding
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoRepository
import io.b306.fitune.viewmodel.MyEditViewModel
import io.b306.fitune.viewmodel.MyEditViewModelFactory
import io.b306.fitune.viewmodel.MyInfoViewModel
import io.b306.fitune.viewmodel.MyInfoViewModelFactory

class MyEditFragment : Fragment() {

    private var _binding: FragmentMyEditBinding? = null
    private val binding get() = _binding!!

    // ViewModel과 Repository 인스턴스
    private lateinit var viewModel: MyEditViewModel
    private lateinit var viewModelFactory: MyEditViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Repository와 ViewModelFactory 인스턴스 생성
        val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()
        val repository = MyInfoRepository(myInfoDao)
        viewModelFactory = MyEditViewModelFactory(repository, ApiObject.patchRetrofitMyEditService)

        // ViewModel 인스턴스 가져오기
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyEditViewModel::class.java)

        // ViewModel의 LiveData 옵저빙
        viewModel.myInfo.observe(viewLifecycleOwner) { myInfo ->
            // UI 업데이트 로직
            binding.tvMyeditEmail.text = myInfo?.email ?: "이메일 가져오기 오류"
            binding.tvMyeditNickname.text = myInfo?.nickname ?: "닉네임 가져오기 오류"
            binding.tvMyeditCellName.text = myInfo?.cellName ?: "세포 이름 가져오기 오류"
            binding.tvMyeditHeight.text = "${myInfo?.height.toString()} cm"
            binding.tvMyeditWeight.text = "${myInfo?.weight.toString()} kg"
            binding.tvMyeditAge.text = "${myInfo?.age.toString()} 세"
        }

        // UserInfo 가져오기
        viewModel.fetchMyInfo()

//        binding.btnModifyNickname.setOnClickListener {
//            showEditDialog("닉네임 변경", InputType.TYPE_CLASS_TEXT) { newValue ->
//                viewModel.updateNickname(userSeq, newValue)
//            }
//        }

        binding.btnModifyCellname.setOnClickListener{
            showEditDialog("세포 이름 변경", InputType.TYPE_CLASS_TEXT) { newValue ->
                viewModel.myInfo.value?.let { currentUser ->
                    val userSeq = currentUser.userSeq ?: 0

                    // 서버 업데이트
                    viewModel.updateCellName(userSeq, newValue)
                }
            }
        }
        binding.btnModifyHeight.setOnClickListener{
            showEditDialog("키 변경", InputType.TYPE_CLASS_NUMBER) { newValue ->
                try {
                    val newHeight = newValue.toInt()
                    if (newHeight in 50..250) {  // 여기서 50과 250은 허용 가능한 최소 및 최대 키를 나타냅니다.
                        viewModel.myInfo.value?.let { currentUser ->
                            val userSeq = currentUser.userSeq ?: 0

                            // 서버 업데이트
                            viewModel.updateHeight(userSeq, newHeight)
                        }
                    } else {
                        Toast.makeText(requireContext(), "유효한 키를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(requireContext(), "유효한 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnModifyWeight.setOnClickListener{
            showEditDialog("몸무게 변경", InputType.TYPE_CLASS_NUMBER) { newValue ->
                try {
                    val newWeight = newValue.toInt()
                    if (newWeight in 30..300) {
                        viewModel.myInfo.value?.let { currentUser ->
                            val userSeq = currentUser.userSeq ?: 0

                            // 서버 업데이트
                            viewModel.updateWeight(userSeq, newWeight)
                        }
                    } else {
                        Toast.makeText(requireContext(), "유효한 몸무게를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    // 입력 값이 정수로 변환할 수 없는 경우, 사용자에게 알림을 보여줍니다.
                    Toast.makeText(context, "올바른 몸무게 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
//        binding.btnModifyAge.setOnClickListener{
//            showEditDialog("나이 변경", InputType.TYPE_CLASS_NUMBER) { newValue ->
//                try {
//                    val newAge = newValue.toInt()
//                    if (newAge in 5..100) {
//                        viewModel.myInfo.value?.let { currentUser ->
//                            val userSeq = currentUser.userSeq ?: 0
//
//                            // 서버 업데이트
//                            viewModel.updateAge(userSeq, newAge)
//                        }
//                    } else {
//                        Toast.makeText(requireContext(), "유효한 나이를 입력해주세요.", Toast.LENGTH_SHORT).show()
//                    }
//                } catch (e: NumberFormatException) {
//                    // 입력 값이 정수로 변환할 수 없는 경우, 사용자에게 알림을 보여줍니다.
//                    Toast.makeText(context, "올바른 나이 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    // 정보 수정 Dialog
    private fun showEditDialog(title: String, inputType: Int, updateFunction: (String) -> Unit) {
        val editText = EditText(requireContext())
        editText.inputType = inputType

        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setView(editText)
            .setPositiveButton("변경") { _, _ ->
                val newValue = editText.text.toString()
                if (newValue.isNotEmpty()) {
                    // 해당 변경 함수 호출
                    updateFunction(newValue)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MyEditFragment()
    }

}