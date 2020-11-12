package by.bsuir.ivan_bondarau.forum.repository

import android.content.Context
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.service.UserService
import dagger.hilt.android.qualifiers.ApplicationContext
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao,
    @ApplicationContext context: Context
) {

    init {
        userDao.deleteAll()
        userDao.insert(
            User(
                id = 1, username = "admin", email = "admin", password =
                BCrypt.hashpw("admin", BCrypt.gensalt())
            )
        )
    }

    fun findByUsername(username: String): User? = userDao.findByUsername(username)

    fun create(user: User) {
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val loaded = userService.insert(user).execute().body()
        if (loaded != null) {
            user.id = loaded.id
        }
    }

    fun isUsernameTaken(username: String): Boolean = userDao.findByUsername(username) != null

}