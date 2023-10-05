package io.b306.fitune.room

import android.app.Application

// 앱이 시작할 때 아래 로직이 시작
// 즉, 데이터베이스가 있으면 가져오고 없으면 생성
// 싱글톤 패턴이라 위처럼 작동함
class FituneApp:Application() {
    val db by lazy {
        FituneDatabase.getInstance(this)
    }
}