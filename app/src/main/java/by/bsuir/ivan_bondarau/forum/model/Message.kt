package by.bsuir.ivan_bondarau.forum.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.bsuir.ivan_bondarau.forum.converter.TimestampConverter
import java.util.*

@Entity
@TypeConverters(TimestampConverter::class)
data class Message(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "authorId") val authorId: Int,
    @ColumnInfo(name = "created") val created: Date?,
    @ColumnInfo(name = "topicId") val  topicId: Int
)