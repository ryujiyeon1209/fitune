package io.b306.fitune.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName: Int,
    val userImageResource: Int,
    val userLevel: String,
    val userBpm: Int,
) : Parcelable