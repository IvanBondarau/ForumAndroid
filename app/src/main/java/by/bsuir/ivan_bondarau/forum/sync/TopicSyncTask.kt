package by.bsuir.ivan_bondarau.forum.sync

import android.util.Log
import by.bsuir.ivan_bondarau.forum.dao.LabelDao
import by.bsuir.ivan_bondarau.forum.dao.TopicDao
import by.bsuir.ivan_bondarau.forum.model.TopicWithLabels
import by.bsuir.ivan_bondarau.forum.observer.Observable
import by.bsuir.ivan_bondarau.forum.observer.Observer
import by.bsuir.ivan_bondarau.forum.queue.TopicQueue
import by.bsuir.ivan_bondarau.forum.service.TopicService
import java.util.TimerTask
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.withLock

@Singleton
class TopicSyncTask @Inject constructor (
    private val topicDao: TopicDao,
    private val labelDao: LabelDao,
    private val topicService: TopicService,
    private val topicQueue: TopicQueue
): TimerTask(), Observable {
    override fun run() {


        topicQueue.lock.withLock {
            topicQueue.queue.forEach {
                topic -> topicService.insert(topic = TopicWithLabels(topic, setOf()).toDto()).execute().body()
            }
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