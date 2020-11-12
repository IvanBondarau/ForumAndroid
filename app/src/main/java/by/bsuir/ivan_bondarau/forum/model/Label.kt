package by.bsuir.ivan_bondarau.forum.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(
        value = ["name"],
        unique = true
    )]
)
data class Label(
    @PrimaryKey(autoGenerate = true) var labelId: Int?,
    @ColumnInfo(name = "name") val name: String
)