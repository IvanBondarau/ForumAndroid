package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.bsuir.ivan_bondarau.forum.model.Topic

@Dao
interface TopicDao {

    @Insert
    fun insert(topic: Topic)

    @Query("select * from topic")
    fun findAll(): List<Topic>

    @Query("select * from topic where id = :id")
    fun findById(id: Int): Topic?
}