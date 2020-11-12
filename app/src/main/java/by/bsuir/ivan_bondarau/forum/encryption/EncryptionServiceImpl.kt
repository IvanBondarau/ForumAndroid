package by.bsuir.ivan_bondarau.forum.encryption

import org.mindrot.jbcrypt.BCrypt

class EncryptionServiceImpl : EncryptionService {

    override fun encrypt(str: String): String {
        return BCrypt.hashpw(str, BCrypt.gensalt(16))
    }

    override fun checkPasswords(plain: String, encrypted: String): Boolean {
        return BCrypt.checkpw(plain, encrypted)
    }
}