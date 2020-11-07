package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.*
import by.bsuir.ivan_bondarau.forum.model.Message

@Dao
interface MessageDao {

    @Query("select * from message")
    fun findAll(): List<Message>

    @Query("select * from message where id = :id limit 1")
    fun findById(id: Int): Message?

    @Insert
    @Transaction
    fun insert(message: Message)

    @Query("select * from message where topicId = :topicId order by created desc")
    @Transaction
    fun findByTopicId(topicId: Int): List<Message>

    @Query("select * from message where topicId = :topicId order by created desc limit 1")
    fun findTopicLastMessage(topicId: Int): Message?

    @Query("select count(*) from message where topicId = :topicId")
    fun countTopicMessages(topicId: Int): Int

    @Delete
    fun delete(message: Message)

    @Query("DELETE FROM MESSAGE")
    fun deleteAll()
}