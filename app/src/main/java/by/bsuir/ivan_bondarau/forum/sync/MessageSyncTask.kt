package by.bsuir.ivan_bondarau.forum.sync

import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.observer.Observable
import by.bsuir.ivan_bondarau.forum.observer.Observer
import by.bsuir.ivan_bondarau.forum.queue.MessageQueue
import by.bsuir.ivan_bondarau.forum.service.MessageService
import java.util.*
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock


class MessageSyncTask constructor
    (
    private val messageDao: MessageDao,
    private val messageService: MessageService,
    private val messageQueue: Queue<Message>,
    private val messageQueueLock: Lock,
    private val messageUpdateQueue: MessageQueue
) : TimerTask(), Observable {

    override fun run() {
        messageUpdateQueue.lock.withLock {
            messageUpdateQueue.queue.forEach {
                messageService.update(it.id!!, it).execute()
                messageDao.update(it)
            }

            messageUpdateQueue.queue.clear()
        }

        messageQueueLock.withLock {
            messageQueue.forEach {
                messageService.insert(it).execute()
            }
            messageQueue.clear()
            messageService.findAll().execute().body()?.forEach {
                if (messageDao.findById(it.id!!) == null) {
                    messageDao.insert(it)
                } else {
                    messageDao.update(it)
                }
            }
        }
        notifyObserver()
    }

    private val observers = mutableListOf<Observer>()

    override fun attach(observer: Observer) {
        observers.add(observer)
    }

    override fun notifyObserver() {
        observers.forEach {
            it.update()
        }
    }


}