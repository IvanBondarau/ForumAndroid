package by.bsuir.ivan_bondarau.forum.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.bsuir.ivan_bondarau.forum.model.Label

@Dao
interface LabelDao {
    @Query("select * from label where name = :name")
    fun findByName(name: String): Label?

    @Insert
    fun insert(label: Label)
}