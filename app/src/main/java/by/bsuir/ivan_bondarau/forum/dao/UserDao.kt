package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.bsuir.ivan_bondarau.forum.model.User

@Dao
interface UserDao {
    @Query("select * from user where username = :username limit 1")
    fun findByUsername(username: String): User?

    @Query("select * from user where id = :id limit 1")
    fun findById(id: Int): User?

    @Insert
    fun insert(user: User)
    @Query("delete from user")
    fun deleteAll()
}