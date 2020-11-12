package by.bsuir.ivan_bondarau.forum.repository

import android.content.Context
import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import java.util.concurrent.locks.Lock
import javax.inject.Inject
import kotlin.concurrent.withLock

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val userDao: UserDao,
    private val messageQueue: Queue<Message>,
    private val messageQueueLock: Lock) {

    init {
        messageDao.deleteAll()
    }


    fun findAll(): List<MessageWithAuthor> {

        messageQueueLock.withLock {

            return messageDao.findAll().map { message ->
                MessageWithAuthor(message, userDao.findById(message.authorId))
            }.union(
                messageQueue.map {message ->
                    MessageWithAuthor(message, userDao.findById(message.authorId))
                }
            ).toList()
        }

    }

    fun findAllByTopicId(topicId: Int): List<MessageWithAuthor> {
        messageQueueLock.withLock {
            return messageDao.findByTopicId(topicId).map {
                    message -> MessageWithAuthor(message, userDao.findById(message.authorId))
            }.union(
                messageQueue.filter {
                    message -> message.topicId == topicId
                }.map {
                    message -> MessageWithAuthor(message, userDao.findById(message.authorId))
                }
            ).toList()
        }
    }

    fun save(message: Message) {
        messageQueueLock.withLock {
            messageQueue.add(message)
        }
    }
}