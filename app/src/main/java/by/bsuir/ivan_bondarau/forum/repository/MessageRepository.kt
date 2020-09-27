package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.User
import java.util.*
import kotlin.collections.HashMap

class MessageRepository {

    private val items: HashMap<Int, Message> = hashMapOf()

    init {
        items[1] = Message(
            text = "ABA",
            author = User("Test user 1", "test"),
            created = Calendar.getInstance().time
        )
        items[2] = Message(
            text = "CABA",
            author = User("Test user 2", "test"),
            created = Calendar.getInstance().time
        )
    }

    fun getById(id: Int): Message {
        return items[id] ?: throw RuntimeException()
    }

    fun findAll(): List<Message>  {
        return items.values.toList()
    }

    companion object {
        val Instance = MessageRepository()
    }


}