package by.bsuir.ivan_bondarau.forum.sync

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.work.Worker
import androidx.work.WorkerParameters
import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.service.MessageService

import androidx.hilt.work.WorkerInject
import by.bsuir.ivan_bondarau.forum.observer.Observable
import by.bsuir.ivan_bondarau.forum.observer.Observer
import java.util.*
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock


public class MessageSyncTask constructor
     (
        private val messageDao: MessageDao,
        private val messageService: MessageService,
        private val messageQueue: Queue<Message>,
        private val messageQueueLock: Lock
    ) : TimerTask(), Observable {

    override fun run() {
        messageQueueLock.withLock {
            messageQueue.forEach {
                messageService.insert(it).execute()
            }
            messageQueue.clear()
            messageService.findAll().execute().body()?.forEach {
                if (messageDao.findById(it.id!!) == null) {
                    messageDao.insert(it)
                }
            }
        }
        Log.d("Message sync", "Tuta")
        notifyObserver()
    }

    private val observers = mutableListOf<Observer>()

    override fun attach(observer: Observer) {
        observers.add(observer)
    }

    override fun notifyObserver() {
        observers.forEach {
            Log.d("MessageSyncWorker", "Notified")
            it.update()
        }
    }


}