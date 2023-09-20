package io.b306.fitune

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val context: Context, val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = userList[position]
        holder.bind(userData)
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
