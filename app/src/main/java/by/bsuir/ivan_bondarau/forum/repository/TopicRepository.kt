package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.TopicDao
import by.bsuir.ivan_bondarau.forum.model.Topic
import java.util.*
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val topicDao: TopicDao,
    private val messageDao: MessageDao
) {


    fun create(topic: Topic) {
        val id = topicDao.insert(topic)
        topic.id = id.toInt()
    }

    fun findAll(): List<Topic>
        = topicDao.findAll()
        .map {
            it ->
            it.lastMessageDate = messageDao.findTopicLastMessage(it.id!!)?.created
            it.messagesCount = messageDao.countTopicMessages(it.id!!)
            it
        }
}