package io.b306.fitune.room

class MyInfoRepository(private val myInfoDao: MyInfoDao) {
    suspend fun getUserInfo(): MyInfoEntity? {
        return myInfoDao.getMyInfo()
    }

    suspend fun updateUserInfo(updatedUser: MyInfoEntity) {
        myInfoDao.update(updatedUser)
        // 원격 업데이트 로직(API Call)을 여기에 추가할 수 있습니다.
    }
}