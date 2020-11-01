package by.bsuir.ivan_bondarau.forum.validator

import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.repository.UserRepository

class UserSignUpValidator(private val userRepository: UserRepository) {
    fun validate(user: User): String {
        if (user.password != user.passwordRepeat) {
            return "Passwords doesn't match"
        }

        if (userRepository.isUsernameTaken(user.username)) {
            return "This username is already taken"
        }

        return "OK"
    }
}