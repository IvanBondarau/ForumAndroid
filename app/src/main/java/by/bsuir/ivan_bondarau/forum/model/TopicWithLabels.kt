package by.bsuir.ivan_bondarau.forum.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import by.bsuir.ivan_bondarau.forum.dto.TopicDto

data class TopicWithLabels(
    @Embedded
    val topic: Topic,

    @Relation(
        associateBy = Junction(TopicLabelCrossRef::class),
        parentColumn = "topicId",
        entityColumn = "labelId"
    )
    val labels: Set<Label>

) {
    fun toDto(): TopicDto = TopicDto(
        this.topic.topicId,
        this.topic.name,
        this.topic.creationDate,
        this.labels.toList()
    )
}