package io.b306.fitune.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import io.b306.fitune.adapter.ExerciseAdapter
import io.b306.fitune.model.ExerciseList
import io.b306.fitune.databinding.FragmentTutorial2Binding
import io.b306.fitune.room.FituneDatabase
import io.b306.fitune.room.MyInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Tutorial2Fragment : Fragment() {

    private var _binding: FragmentTutorial2Binding? = null
    private val binding get() = _binding!!

    interface TutorialPageNavigator {
        fun moveToNextPage()
    }

    private var pageNavigator: TutorialPageNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TutorialPageNavigator) {
            pageNavigator = context
        } else {
            throw RuntimeException("$context must implement TutorialPageNavigator")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvExerciseList.apply {
            layoutManager = GridLayoutManager(context, 3) // 2개의 컬럼을 가진 그리드 레이아웃
            adapter = ExerciseAdapter(ExerciseList.list) // ExerciseAdapter는 별도로 정의해야 함
        }

        binding.btnTutorial2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

                // Get an instance of MyInfoDao.
                val myInfoDao = FituneDatabase.getInstance(requireContext()).myInfoDao()

                // Retrieve user info from the database.
                val myInfoEntity = myInfoDao.getMyInfo() ?: MyInfoEntity()

                // Get selected exercise from adapter
                val selectedExercise = (binding.rvExerciseList.adapter as? ExerciseAdapter)?.let {
                    if (it.selectedPosition != -1) it.exercises[it.selectedPosition] else null
                }

                if (selectedExercise != null) {
                    // Set preferred exercise.
                    myInfoEntity.preferExercise = selectedExercise.name
                    myInfoEntity.exerciseListSeq = selectedExercise.exerciseId

                    if (myInfoEntity.id != null) {
                        myInfoDao.update(myInfoEntity)
                        Log.d("현재 유저",myInfoEntity.toString())
                    } else {
                        Log.d("유저의 id 값",myInfoEntity.id.toString())
                    }
                }

            }
            pageNavigator?.moveToNextPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 바인딩 참조를 정리합니다.
        _binding = null
    }

    companion object {
        fun newInstance() = Tutorial2Fragment()
    }
}
