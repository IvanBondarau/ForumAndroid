package by.bsuir.ivan_bondarau.forum

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import by.bsuir.ivan_bondarau.forum.sync.MessageSyncTask
import by.bsuir.ivan_bondarau.forum.sync.TopicSyncTask
import by.bsuir.ivan_bondarau.forum.sync.UserSyncTask
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class ForumApp : Application(), Configuration.Provider {


    private val timer: Timer = Timer();
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    @Inject
    lateinit var messageSyncTask: MessageSyncTask
    @Inject
    lateinit var userSyncTask: UserSyncTask

    @Inject
    lateinit var topicSyncTask: TopicSyncTask

    override fun getWorkManagerConfiguration(): Configuration {

        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        timer.scheduleAtFixedRate(
            messageSyncTask, 0, 1000
        )
        timer.scheduleAtFixedRate(
            userSyncTask, 0, 1000
        )
        timer.scheduleAtFixedRate(
            topicSyncTask, 0, 1000
        )
    }

}