package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    fun logIn(username: String, password: String): Boolean{
        return userRepository.exists(User(username, password))
    }

}