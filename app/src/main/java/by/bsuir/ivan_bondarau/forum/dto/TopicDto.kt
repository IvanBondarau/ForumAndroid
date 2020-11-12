package by.bsuir.ivan_bondarau.forum.dto

import by.bsuir.ivan_bondarau.forum.model.Label
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.model.TopicWithLabels
import java.util.*

data class TopicDto(
    var topicId: Int? = null,
    var name: String,
    var creationDate: Date,

    var labels: List<Label>
) {
    fun toEntity(): TopicWithLabels {
        return TopicWithLabels(
            Topic(topicId, name, creationDate),
            labels = labels.toSet()
        )
    }
}