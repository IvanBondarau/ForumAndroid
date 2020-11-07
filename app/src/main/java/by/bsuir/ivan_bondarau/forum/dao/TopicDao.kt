package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import by.bsuir.ivan_bondarau.forum.model.Topic

@Dao
interface TopicDao {

    @Insert
    @Transaction
    fun insert(topic: Topic): Long

    @Query("select * from topic")
    @Transaction
    fun findAll(): List<Topic>

    @Query("select * from topic where id = :id")
    fun findById(id: Int): Topic?

    @Query("DELETE FROM TOPIC")
    fun deleteAll()
}