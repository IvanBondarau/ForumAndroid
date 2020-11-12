package by.bsuir.ivan_bondarau.forum.queue

import by.bsuir.ivan_bondarau.forum.model.Message
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class MessageQueue {
    val lock: Lock = ReentrantLock()
    val queue: Queue<Message> = ConcurrentLinkedQueue<Message>()
}