package by.bsuir.ivan_bondarau.forum.model

import java.util.*

data class Message(
    val text: String? = null,
    val author: User? = null,
    val created: Date? = null
)