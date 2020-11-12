package by.bsuir.ivan_bondarau.forum.model

import androidx.room.*
import by.bsuir.ivan_bondarau.forum.converter.TimestampConverter
import java.util.*

@Entity
@TypeConverters(TimestampConverter::class)
data class Topic(
    @PrimaryKey(autoGenerate = true) var topicId: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "creationDate") val creationDate: Date,
    @Ignore var lastMessageDate: Date? = null,
    @Ignore var messagesCount: Int? = null
) {
    constructor(topicId: Int?, name: String, creationDate: Date)
            : this(topicId, name, creationDate, null, null)


}