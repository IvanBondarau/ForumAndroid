package by.bsuir.ivan_bondarau.forum.sync

import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.queue.MessageQueue
import by.bsuir.ivan_bondarau.forum.queue.TopicQueue
import by.bsuir.ivan_bondarau.forum.service.MessageService
import by.bsuir.ivan_bondarau.forum.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class SyncModule {


    @Provides
    @Singleton
    fun messageSyncWorker(
        messageDao: MessageDao,
        messageService: MessageService,
        messageQueue: Queue<Message>,
        messageQueueLock: Lock,
        messageUpdateQueue: MessageQueue
    ): MessageSyncTask {

        return MessageSyncTask(messageDao, messageService, messageQueue, messageQueueLock, messageUpdateQueue)
    }

    @Provides
    fun userSyncWorker(
        userDao: UserDao,
        userService: UserService
    ): UserSyncTask {

        return UserSyncTask(userService, userDao)
    }


    @Provides
    @Singleton
    fun messageQueue(): Queue<Message> {
        return ConcurrentLinkedQueue<Message>()
    }

    @Provides
    @Singleton
    fun messageQueueLock(): Lock {
        return ReentrantLock()
    }

    @Provides
    @Singleton
    fun topicQueue(): TopicQueue {
        return TopicQueue()
    }

    @Provides
    @Singleton
    fun messageUpdateQueue(): MessageQueue {
        return MessageQueue()
    }


}