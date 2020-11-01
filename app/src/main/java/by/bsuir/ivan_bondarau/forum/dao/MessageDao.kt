package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.bsuir.ivan_bondarau.forum.model.Message

@Dao
interface MessageDao {

    @Query("select * from message")
    fun findAll(): List<Message>

    @Query("select * from message where id = :id limit 1")
    fun findById(id: Int): Message?

    @Insert
    fun insert(message: Message)
}