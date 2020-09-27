package by.bsuir.ivan_bondarau.forum.repository

import by.bsuir.ivan_bondarau.forum.model.User

class UserRepository {

    private val items: MutableList<User> = mutableListOf()

    init {
        items.add(User("admin", "admin"));
    }

    fun exists(user: User): Boolean {
        return items.contains(user)
    }

    companion object {
        val Instance = UserRepository()
    }
}