package io.b306.fitune.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.b306.fitune.model.ExerciseList
import io.b306.fitune.room.ExerciseRecommendEntity
import io.b306.fitune.room.ExerciseRecommendRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ExerciseRecommendViewModel(private val repository: ExerciseRecommendRepository) : ViewModel() {

    // MutableLiveData 또는 StateFlow를 사용하여 UI에 변화를 알릴 수 있음
    private val _myInfo = MutableLiveData<ExerciseRecommendEntity?>()
    val myInfo: LiveData<ExerciseRecommendEntity?> get() = _myInfo

    fun fetchMyInfo(id: Int) {
        viewModelScope.launch {
            val result: ExerciseRecommendEntity? =
                repository.fetchExerciseRecommendById(id).firstOrNull()
            _myInfo.value = result
        }
    }


}