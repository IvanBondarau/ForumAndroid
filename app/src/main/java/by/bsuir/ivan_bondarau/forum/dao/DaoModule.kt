package by.bsuir.ivan_bondarau.forum.dao

import by.bsuir.ivan_bondarau.forum.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DaoModule {

    @Provides
    fun userDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun messageDao(appDatabase: AppDatabase): MessageDao {
        return appDatabase.messageDao()
    }

    @Provides
    fun topicDao(appDatabase: AppDatabase): TopicDao {
        return appDatabase.topicDao()
    }

}