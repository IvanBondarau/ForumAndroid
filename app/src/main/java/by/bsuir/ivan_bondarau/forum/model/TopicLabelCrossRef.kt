package by.bsuir.ivan_bondarau.forum.model

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["topicId", "labelId"],
indices = [Index("topicId"), Index("labelId")])
data class TopicLabelCrossRef (
    val topicId: Int,
    val labelId: Int
)