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
import io.b306.fitune.databinding.FragmentMyEditBinding

class MyEditFragment : Fragment() {

    private var _binding: FragmentMyEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnModifyNickname.setOnClickListener {
            showEditDialog("닉네임 변경", InputType.TYPE_CLASS_TEXT) {
                // updateNickname(it) or 다른 적절한 로직
                // API 통신 여기다 적으면 됨
            }
        }
        binding.btnModifyCellname.setOnClickListener{
            showEditDialog("세포 이름 변경", InputType.TYPE_CLASS_TEXT) {
                // updateNickname(it) or 다른 적절한 로직
                // API 통신 여기다 적으면 됨
            }
        }
        binding.btnModifyHeight.setOnClickListener{
            showEditDialog("키 변경", InputType.TYPE_CLASS_NUMBER) {
                // updateNickname(it) or 다른 적절한 로직
                // API 통신 여기다 적으면 됨
            }
        }
        binding.btnModifyWeight.setOnClickListener{
            showEditDialog("몸무게 변경", InputType.TYPE_CLASS_NUMBER) {
                // updateNickname(it) or 다른 적절한 로직
                // API 통신 여기다 적으면 됨
            }
        }
        binding.btnModifyAge.setOnClickListener{
            showEditDialog("나이 변경", InputType.TYPE_CLASS_NUMBER) {
                // updateNickname(it) or 다른 적절한 로직
                // API 통신 여기다 적으면 됨
            }
        }
    }

    // 정보 수정 Dialog
    fun showEditDialog(title: String, inputType: Int, updateFunction: (String) -> Unit) {
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
                    Toast.makeText(requireContext(), newValue, Toast.LENGTH_LONG).show()
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