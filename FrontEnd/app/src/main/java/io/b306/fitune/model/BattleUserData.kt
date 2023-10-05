package io.b306.fitune.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BattleUserData(
    val userSeq: Int,
    val cellExp: Int,
    val cellName: String,
    val height: Int,
    val weight: Int,
    val bpm: Int
    ) : Parcelable
