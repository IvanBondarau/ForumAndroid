package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import by.bsuir.ivan_bondarau.forum.model.User
import java.util.*
import javax.inject.Inject

class MessageRepository @Inject constructor(private val messageDao: MessageDao, private val userDao: UserDao) {



    fun findAll(): List<MessageWithAuthor>
        = messageDao.findAll().map {
            message -> MessageWithAuthor(message, userDao.findById(message.authorId))
        }

    fun findAllByTopicId(topicId: Int): List<MessageWithAuthor>
        = messageDao.findByTopicId(topicId).map {
            message -> MessageWithAuthor(message, userDao.findById(message.authorId))
    }

    fun save(message: Message) {
        messageDao.insert(message)
    }
}