package by.bsuir.ivan_bondarau.forum.model

import androidx.room.*
import by.bsuir.ivan_bondarau.forum.converter.TimestampConverter
import java.util.*

@Entity
@TypeConverters(TimestampConverter::class)
data class Message(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "creationDate") val creationDate: Date,
    @ColumnInfo(name = "topicId") val topicId: Int,
    @ColumnInfo(name = "authorId") val authorId: Int,
    @ColumnInfo(name = "likes") var likes: Int = 0,
    @Ignore var isLiked: Boolean = false
) {
    constructor(id: Int?, text: String, creationDate: Date, topicId: Int, authorId: Int, likes: Int)
            : this(id, text, creationDate, topicId, authorId, likes, false)
}