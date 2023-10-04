package io.b306.fitune.room

class MyInfoRepository(private val myInfoDao: MyInfoDao) {
    suspend fun getUserInfo(): MyInfoEntity? {
        return myInfoDao.getMyInfo()
    }

    suspend fun updateUserInfo(updatedUser: MyInfoEntity) {
        myInfoDao.update(updatedUser)
    }

    suspend fun deleteUserInfo() {
        myInfoDao.deleteAll()
    }
}