package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.repository.UserRepository
import by.bsuir.ivan_bondarau.forum.validator.UserSignUpValidator
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val validator: UserSignUpValidator = UserSignUpValidator(userRepository)

    fun createAccount(user: User): String {
        val validationResult = validator.validate(user)
        if (validationResult != "OK") {
            return validationResult
        }
        userRepository.create(user)
        return "OK"
    }


}