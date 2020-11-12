package by.bsuir.ivan_bondarau.forum.sync

import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.service.UserService
import java.util.*

class UserSyncTask(
    private val userService: UserService,
    private val userDao: UserDao
) : TimerTask() {

    override fun run() {
        userService.findAll().execute().body()?.forEach {
            if (userDao.findById(it.id!!) == null) {
                userDao.insert(it)
            }
        }
    }
}