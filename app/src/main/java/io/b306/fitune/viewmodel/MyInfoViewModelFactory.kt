package io.b306.fitune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.room.MyInfoRepository

class MyInfoViewModelFactory(private val repository: MyInfoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyInfoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}