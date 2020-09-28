package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.User

class UserRepository(private val userDao: UserDao) {


    init {
        if (userDao.findByUsername("admin") == null) {
            userDao.insert(User(1, "admin", "admin"))
        }
    }

    fun exists(user: User): Boolean {
        val databaseUser = userDao.findByUsername(user.username)
        return if (databaseUser == null) {
            false
        } else {
            databaseUser.password == user.password
        }
    }

}