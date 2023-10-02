package io.b306.fitune.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.b306.fitune.model.Exercise
import io.b306.fitune.R

class ExerciseAdapter(val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseViewHolder>() {

    var selectedPosition = -1  // 선택된 아이템의 위치를 추적합니다.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        // 여기서 ViewHolder를 생성하고 반환합니다.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view).apply {
            itemView.setOnClickListener {
                val previousSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousSelectedPosition)  // 이전 선택된 아이템을 업데이트합니다.
                notifyItemChanged(selectedPosition)  // 새로 선택된 아이템을 업데이트합니다.
            }
        }
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        // 여기서 ViewHolder를 구성합니다.
        val exercise = exercises[position]
        holder.bind(exercise, position == selectedPosition) // 선택된 아이템인지를 확인합니다.
    }

    override fun getItemCount(): Int = exercises.size // 아이템 개수를 반환합니다.
}

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // 여기서 ViewHolder의 뷰를 구성하고 관리합니다.
    private val imageView: ImageView = view.findViewById(R.id.exerciseImage)
    private val textView: TextView = view.findViewById(R.id.exerciseName)

    fun bind(exercise: Exercise, isSelected: Boolean) {
        imageView.setImageResource(exercise.imageResId)
        textView.text = exercise.name

        // 아이템이 선택된 경우와 그렇지 않은 경우에 대한 backgroundTint 설정
        if (isSelected) {
            itemView.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, R.color.default_text)
            )
        } else {
            itemView.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, R.color.white)
            )
        }
    }
}