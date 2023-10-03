package io.b306.fitune.room

class MyInfoRepository(private val myInfoDao: MyInfoDao) {
    suspend fun getUserInfo(): MyInfoEntity? {
        return myInfoDao.getMyInfo()
    }
}