package by.bsuir.ivan_bondarau.forum.model

import androidx.room.*

@Entity(
    indices = [Index(
        value = ["username"],
        unique = true
    )]
)
data class User(
    @PrimaryKey var id: Int? = null,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email")    var email: String,
    @ColumnInfo(name = "password") var password: String,
    @Ignore                        var passwordRepeat: String? = null
) {
    constructor(id: Int?, username: String, email: String, password: String)
        : this(id, username, email, password, null)

}