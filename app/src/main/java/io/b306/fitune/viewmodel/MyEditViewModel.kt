package io.b306.fitune.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.b306.fitune.api.MyEditAPI
import io.b306.fitune.room.MyInfoEntity
import io.b306.fitune.room.MyInfoRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyEditViewModel(
    private val repository: MyInfoRepository,
    private val myEditAPI: MyEditAPI  // 주입받거나 얻는 코드 추가
) : ViewModel() {

    // MutableLiveData 또는 StateFlow를 사용하여 UI에 변화를 알릴 수 있음
    private val _myInfo = MutableLiveData<MyInfoEntity?>()
    val myInfo: LiveData<MyInfoEntity?> get() = _myInfo

    // 로컬 DB에서 사용자 정보 가져오기
    fun fetchMyInfo() {
        viewModelScope.launch {
            _myInfo.value = repository.getUserInfo()
        }
    }

    // 로컬 DB만 업데이트 - 사용 안 함
    fun updateMyInfo(updatedInfo: MyInfoEntity) {
        viewModelScope.launch {
            repository.updateUserInfo(updatedInfo)
            // 필요하다면, API 콜로 원격 데이터베이스도 업데이트
        }
    }

    // 서버로 닉네임 업데이트 - 스웨거에 없음
//    fun updateNickname(userSeq: Int, newNickname: String) {
//        updateValueThroughAPI(
//            { myEditAPI.updateNickname(userSeq, newNickname) }, // API 호출
//            { it.copy(nickname = newNickname) } // 로컬 데이터 업데이트
//        )
//    }

    // 서버로 세포 이름 업데이트
    fun updateCellName(userSeq: Int, newCellName: String) {
        updateValueThroughAPI(
            { myEditAPI.updateCellName(userSeq, newCellName) },
            { it.copy(cellName = newCellName) }
        )
    }

    // 서버로 키 업데이트
    fun updateHeight(userSeq: Int, newHeight: Int) {
        updateValueThroughAPI(
            { myEditAPI.updateHeight(userSeq, newHeight) },
            { it.copy(height = newHeight) }
        )
    }

    // 서버로 몸무게 업데이트
    fun updateWeight(userSeq: Int, newWeight: Int) {
        updateValueThroughAPI(
            { myEditAPI.updateWeight(userSeq, newWeight) },
            { it.copy(weight = newWeight) }
        )
    }

    // 서버로 선호 운동 업데이트 - 이거 수정해야 됨(Int로 줘야 하는데 어떤 운동의 Int가 뭐인지)
    fun updatePrefer(userSeq: Int, newPrefer: Int) {
        updateValueThroughAPI(
            { myEditAPI.updatePrefer(userSeq, newPrefer) },
            { it.copy(preferExercise = newPrefer.toString()) }
        )
    }

    // 서버로 운동 강도 업데이트
    fun updateTension(userSeq: Int, newTension: Int) {
        updateValueThroughAPI(
            { myEditAPI.updateTension(userSeq, newTension) },
            { it.copy(tension = newTension) }
        )
    }

    private fun updateValueThroughAPI(
        apiCall: suspend () -> Response<Void>,
        updateLocalUser: (MyInfoEntity) -> MyInfoEntity
    ) {
        viewModelScope.launch {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    _myInfo.value?.let { currentUser ->
                        // 로컬 DB 업데이트
                        val updatedUser = updateLocalUser(currentUser)
                        repository.updateUserInfo(updatedUser)
                        _myInfo.value = updatedUser
                    }
                } else {
                    // API 호출 실패 처리
                    Log.e("정보 수정 API 호출 실패", "ㅠ")
                }
            } catch (e: Exception) {
                // 예외 처리 (예: 네트워크 에러)
                Log.e("정보 수정 네트워크 에러", "ㅠ")
            }
        }
    }

}