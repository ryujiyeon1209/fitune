package io.b306.fitune.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class FightRecordData (
    val battleRecordSeq: Int,
    val battleDate: String,
    val winnerName: String,
    val battleOtherSeq: Int,
    val battleOtherName: String,
) : Parcelable

