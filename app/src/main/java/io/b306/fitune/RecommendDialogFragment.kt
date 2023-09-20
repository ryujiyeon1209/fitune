package io.b306.fitune

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

class RecommendDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_recommend_dialog, null)

        // 여기에서 view의 요소를 조작할 수 있습니다.
        // 예: val button = view.findViewById<Button>(R.id.some_button_id)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}