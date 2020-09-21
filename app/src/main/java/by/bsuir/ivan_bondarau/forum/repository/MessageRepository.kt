package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.User
import java.util.*
import kotlin.collections.HashMap

class MessageRepository {

    private val items: HashMap<Int, Message> = hashMapOf()

    init {
        items[1] = Message(
            text = "Lorem ipsum dolor sit amet, no sit sonet noster dignissim, sed omnes omnesque ea, at movet admodum luptatum cum. Pri no malis petentium. Erat facer harum at ius, et duo amet feugiat. Et mutat simul mel, est eu amet senserit reprimique. Meis tempor euripidis et vel, cum viris nihil an.\n",
            author = User("Test user 1"),
            created = Calendar.getInstance().time
        )
        items[2] = Message(
            text = "Lorem ipsum dolor sit amet, no sit sonet noster dignissim, sed omnes omnesque ea, at movet admodum luptatum cum. Pri no malis petentium. Erat facer harum at ius, et duo amet feugiat. Et mutat simul mel, est eu amet senserit reprimique. Meis tempor euripidis et vel, cum viris nihil an.\n",
            author = User("Test user 2"),
            created = Calendar.getInstance().time
        )
    }

    fun getById(id: Int): Message {
        return items[id] ?: throw RuntimeException()
    }

    companion object {
        val Instance = MessageRepository()
    }


}