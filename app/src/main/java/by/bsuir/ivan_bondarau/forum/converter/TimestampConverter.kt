package by.bsuir.ivan_bondarau.forum.converter

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimestampConverter {
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) df.parse(value) else null
    }

    @TypeConverter
    fun toTimestamp(value: Date?): String? {
        return if (value != null) df.format(value) else null
    }
}