package io.b306.fitune.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.b306.fitune.room.MyInfoEntity
import io.b306.fitune.room.MyInfoRepository
import kotlinx.coroutines.launch

class MyInfoViewModel(private val repository: MyInfoRepository) : ViewModel() {

    // MutableLiveData 또는 StateFlow를 사용하여 UI에 변화를 알릴 수 있음
    private val _myInfo = MutableLiveData<MyInfoEntity?>()
    val myInfo: LiveData<MyInfoEntity?> get() = _myInfo

    fun fetchMyInfo() {
        viewModelScope.launch {
            _myInfo.value = repository.getUserInfo()
        }
    }
}