package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.User
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class UserRepository @Inject constructor( private val userDao: UserDao) {


    init {
        if (userDao.findByUsername("admin") == null) {
            create(User(1, "admin", "admin@admin.com","admin"))
        }

        if (userDao.findByUsername("admin1") == null) {
            create(User(2, "admin1", "admin@admin.com","admin"))
        }
    }

    fun findByUsername(username: String): User?
        = userDao.findByUsername(username)

    fun create(user: User) {
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
        userDao.insert(user);
    }

    fun isUsernameTaken(username: String) : Boolean
        = userDao.findByUsername(username) != null

}