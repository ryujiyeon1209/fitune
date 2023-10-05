package io.b306.fitune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.room.ExerciseRecommendRepository

class ExerciseRecommendViewModelFactory(
    private val repository: ExerciseRecommendRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseRecommendViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseRecommendViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}