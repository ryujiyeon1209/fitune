package io.b306.fitune

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FightResultAdapter (
    private val fragment: Fragment,
    private val fightResultDataList: ArrayList<FightResultData>,
    private val onUserClick: (FightResultData) -> Unit
) :

    RecyclerView.Adapter<FightResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_fight_result, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fightResultDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fightResultData = fightResultDataList[position]
        holder.bind(fightResultData)
        holder.itemView.setOnClickListener {
            onUserClick(fightResultData) // 여기서 클릭 이벤트를 처리
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fightDateTextView = itemView.findViewById<TextView>(R.id.tv_fight_date)
        private val fightUserNameTextView = itemView.findViewById<TextView>(R.id.tv_fight_user_name)
        private val fightResultTextView = itemView.findViewById<TextView>(R.id.tv_fight_result)
        fun bind(fightResultData: FightResultData) {
            fightDateTextView.text = fightResultData.fightDate
            fightUserNameTextView.text = fightResultData.fightUserName
            fightResultTextView.text = fightResultData.fightResult
        }
    }

}
