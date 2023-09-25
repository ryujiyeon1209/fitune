package io.b306.fitune

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(private val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        // 여기서 ViewHolder를 생성하고 반환합니다.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        // 여기서 ViewHolder를 구성합니다.
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int = exercises.size // 아이템 개수를 반환합니다.
}

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // 여기서 ViewHolder의 뷰를 구성하고 관리합니다.
    private val imageView: ImageView = view.findViewById(R.id.exerciseImage)
    private val textView: TextView = view.findViewById(R.id.exerciseName)

    fun bind(exercise: Exercise) {
        imageView.setImageResource(exercise.imageResId)
        textView.text = exercise.name
    }
}