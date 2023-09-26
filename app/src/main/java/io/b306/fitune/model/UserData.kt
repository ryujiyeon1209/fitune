package io.b306.fitune.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName: String,
    val userImageResource: Int,
    val userLevel: Int,
    val userBpm: Int
) : Parcelable