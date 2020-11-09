package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.LabelDao
import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.TopicDao
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.model.TopicWithLabels
import by.bsuir.ivan_bondarau.forum.service.TopicService
import kotlinx.coroutines.awaitAll
import retrofit2.await
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val topicDao: TopicDao,
    private val messageDao: MessageDao,
    private val labelDao: LabelDao,
    private val topicService: TopicService
) {
    init {
        topicDao.deleteAll()
    }

    private  fun sync() {
        topicService.findAll().execute().body()?.map {
            it.toEntity()
        }?.forEach { topic ->
            topic.labels.forEach {
                if (labelDao.findByName(it.name) == null)
                    labelDao.insert(label = it)
            }
            if (topicDao.findById(topic.topic.topicId!!) == null) {
                topicDao.insert(topic = topic.topic)
            }
        }
    }

    fun create(topic: Topic) {
        sync()
        val result = topicService.insert(topic = TopicWithLabels(topic, setOf()).toDto()).execute().body()

        topic.topicId = result!!.topicId
        topicDao.insert(topic)


    }

     fun findAll(): List<Topic> {
        sync()
        return topicDao.findAll()
            .map { it ->
                it.lastMessageDate = messageDao.findTopicLastMessage(it.topicId!!)?.creationDate
                it.messagesCount = messageDao.countTopicMessages(it.topicId!!)
                it
            }
    }
}