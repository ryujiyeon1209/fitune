package io.b306.fitune.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.R
import io.b306.fitune.model.UserData

class UserAdapter (
    private val fragment: Fragment,
    private val userList: ArrayList<UserData>,
    private val onUserClick: (UserData) -> Unit
) :

    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_user, parent, false)
        return ViewHolder(view)
    }

//    override fun getItemCount(): Int {
//        return userList.count()
//    }
    override fun getItemCount(): Int = userList.size

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val userData = userList[position]
//        holder.bind(userData)
//        holder.itemView.setOnClickListener {
//            val dialogFragment = UserDetailDialogFragment()
//            val bundle = Bundle()
//            bundle.putParcelable("user", userData)  // UserData는 Parcelable을 구현해야 함 - 했음, gradle 추가도 해야 됨 이거
//            dialogFragment.arguments = bundle
//            dialogFragment.show(fragment.parentFragmentManager, "userDetail")
//        }
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = userList[position]
        holder.bind(userData)
        holder.itemView.setOnClickListener {
            onUserClick(userData) // 여기서 클릭 이벤트를 처리
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView = itemView.findViewById<TextView>(R.id.find_user_name)
        private val userImageView = itemView.findViewById<ImageView>(R.id.find_user_image)
        fun bind(userData: UserData) {
            userNameTextView.text = userData.userName
            userImageView.setImageResource(userData.userImageResource)
        }
    }

}
