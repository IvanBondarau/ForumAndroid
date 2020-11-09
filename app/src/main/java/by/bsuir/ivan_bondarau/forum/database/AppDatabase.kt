package by.bsuir.ivan_bondarau.forum.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.bsuir.ivan_bondarau.forum.dao.LabelDao
import by.bsuir.ivan_bondarau.forum.dao.MessageDao
import by.bsuir.ivan_bondarau.forum.dao.TopicDao
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.*

@Database(entities = [
    User::class,
    Message::class,
    Topic::class,
    Label::class,
    TopicLabelCrossRef::class
], version = 10)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun messageDao(): MessageDao

    abstract fun topicDao(): TopicDao

    abstract fun labelDao(): LabelDao
}