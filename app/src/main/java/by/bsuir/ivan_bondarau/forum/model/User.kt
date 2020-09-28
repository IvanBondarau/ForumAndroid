package by.bsuir.ivan_bondarau.forum.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)