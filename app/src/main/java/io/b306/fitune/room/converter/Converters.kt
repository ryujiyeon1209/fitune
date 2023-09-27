package io.b306.fitune.room.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { dateFormat.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }
}