package io.b306.fitune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.b306.fitune.api.MyEditAPI
import io.b306.fitune.room.MyInfoRepository

class MyEditViewModelFactory(
    private val repository: MyInfoRepository,
    private val myEditAPI: MyEditAPI  // MyEditAPI 인스턴스 추가
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            // MyEditViewModel 생성 시 repository와 myEditAPI를 함께 전달
            return MyEditViewModel(repository, myEditAPI) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}