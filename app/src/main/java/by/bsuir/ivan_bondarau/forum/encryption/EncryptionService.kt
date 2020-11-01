package by.bsuir.ivan_bondarau.forum.encryption

interface EncryptionService {

    fun encrypt(str: String): String

    fun checkPasswords(plain: String, encrypted: String): Boolean
}