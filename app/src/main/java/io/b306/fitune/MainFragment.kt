package io.b306.fitune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

        binding.ivFortune.setOnClickListener {
            val recommendDialogFragment = RecommendDialogFragment()
            // supportFragmentManager 대신 childFragmentManager를 사용해야 합니다.
            recommendDialogFragment.show(childFragmentManager, "recommend_dialog")
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