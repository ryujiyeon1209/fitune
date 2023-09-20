package io.b306.fitune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FightFindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fight_find)

        val userList = arrayListOf(
            UserData("세포1", R.drawable.ic_check_mark),
            UserData("세포2", R.drawable.ic_check_mark),

            // 다른 사용자 데이터도 추가 가능
        )
        val recyclerView = findViewById<RecyclerView>(R.id.rv_user)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = UserAdapter(this, userList)
        recyclerView.adapter = adapter
    }
}