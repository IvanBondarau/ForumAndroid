package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {


    fun logIn(username: String, password: String): User? {
        val user = userRepository.findByUsername(username) ?: return null
        return if (BCrypt.checkpw(password, user.password)) user else null
    }

}