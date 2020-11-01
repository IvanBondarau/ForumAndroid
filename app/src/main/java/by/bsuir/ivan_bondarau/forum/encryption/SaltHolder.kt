package by.bsuir.ivan_bondarau.forum.encryption

import org.mindrot.jbcrypt.BCrypt

class SaltHolder {

    companion object {
        val salt: String by lazy {
            BCrypt.gensalt()
        }
    }
}