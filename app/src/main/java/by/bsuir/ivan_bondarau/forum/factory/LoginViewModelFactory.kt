package by.bsuir.ivan_bondarau.forum.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.repository.UserRepository
import by.bsuir.ivan_bondarau.forum.viewmodel.LoginViewModel

class LoginViewModelFactory private constructor(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }

    companion object {
        val Instance by lazy {
            LoginViewModelFactory(UserRepository.Instance)
        }
    }


}