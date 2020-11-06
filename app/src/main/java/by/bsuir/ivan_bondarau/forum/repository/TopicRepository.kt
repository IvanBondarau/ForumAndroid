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

    init {
        if (topicDao.findById(1) == null) {
            topicDao.insert(
                Topic (
                    1,
                    "Test topic 1",
                    Calendar.getInstance().time
                )
            )
        }

        if (topicDao.findById(2) == null) {
            topicDao.insert(
                Topic (
                    2,
                    "Test topic 2",
                    Calendar.getInstance().time
                )
            )
        }
    }

    fun findAll(): List<Topic>
        = topicDao.findAll()
        .map {
            it ->
            it.lastMessageDate = messageDao.findTopicLastMessage(it.id!!)?.created
            it.messagesCount = messageDao.countTopicMessages(it.id)
            it
        }
}