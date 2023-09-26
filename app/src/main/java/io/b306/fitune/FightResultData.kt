package io.b306.fitune

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FightResultData (
    val fightDate: String,
    val fightUserName: String,
    val fightResult: String,
) : Parcelable