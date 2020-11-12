package by.bsuir.ivan_bondarau.forum.queue

import by.bsuir.ivan_bondarau.forum.model.Topic
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class TopicQueue {
    val lock: Lock = ReentrantLock()
    val queue: Queue<Topic> = ConcurrentLinkedQueue<Topic>()
}