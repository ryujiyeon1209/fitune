package io.b306.fitune.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.R
import io.b306.fitune.model.BattleUserData


class UserAdapter(
    private val fragment: Fragment,
    private val userList: List<BattleUserData>,
    private val onUserClick: (BattleUserData) -> Unit
) :

    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_user, parent, false)
        Log.e("어댑터 출력 확인22", userList.toString())

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = userList[position]
        Log.e("어댑터 출력 확인33", userList.toString())

        holder.bind(userData)
        holder.itemView.setOnClickListener {
            onUserClick(userData) // 여기서 클릭 이벤트를 처리
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView = itemView.findViewById<TextView>(R.id.find_user_name)
        fun bind(battleUserData: BattleUserData) {
            userNameTextView.text = battleUserData.cellName
        }

    }

}
