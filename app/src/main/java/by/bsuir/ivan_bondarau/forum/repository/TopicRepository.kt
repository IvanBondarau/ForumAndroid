package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.TopicDao
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.model.TopicWithLabels
import by.bsuir.ivan_bondarau.forum.service.TopicService
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val topicDao: TopicDao,
    private val messageDao: MessageDao,
    private val topicService: TopicService
) {
    init {
        topicDao.deleteAll()
    }


    fun create(topic: Topic) {
        val result =
            topicService.insert(topic = TopicWithLabels(topic, setOf()).toDto()).execute().body()

        topic.topicId = result!!.topicId
        topicDao.insert(topic)


    }

    fun findById(id: Int): Topic? {
        return topicDao.findById(id)
    }

    fun findAll(): List<Topic> {
        return topicDao.findAll()
            .map { it ->
                it.lastMessageDate = messageDao.findTopicLastMessage(it.topicId!!)?.creationDate
                it.messagesCount = messageDao.countTopicMessages(it.topicId!!)
                it
            }
    }
}