package by.bsuir.ivan_bondarau.forum.model

import java.util.*

data class Message(
    val text: String,
    val author: User,
    val created: Date
)