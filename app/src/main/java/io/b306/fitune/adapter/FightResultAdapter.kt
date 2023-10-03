package io.b306.fitune.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.model.FightResultData
import io.b306.fitune.R
import io.b306.fitune.model.FightRecordData

class FightResultAdapter(
    private val fragment: Fragment,
    private val fightResultDataList: List<FightRecordData>,
    private val onUserClick: (FightRecordData) -> Unit
) :

    RecyclerView.Adapter<FightResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_fight_result, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fightResultDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fightRecordData = fightResultDataList[position]
        holder.bind(fightRecordData)
        holder.itemView.setOnClickListener {
            onUserClick(fightRecordData) // 여기서 클릭 이벤트를 처리
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fightDateTextView = itemView.findViewById<TextView>(R.id.tv_fight_date)
        private val fightUserNameTextView = itemView.findViewById<TextView>(R.id.tv_fight_user_name)
        private val fightResultTextView = itemView.findViewById<TextView>(R.id.tv_fight_result)
        fun bind(fightRecordData: FightRecordData) {
            fightDateTextView.text = fightRecordData.battleDate
            fightUserNameTextView.text = fightRecordData.battleOtherName
            fightResultTextView.text = if (fightRecordData.battleOtherName == fightRecordData.winnerName) {
                "패배"
            } else {
                "승리"
            }}
    }

}
