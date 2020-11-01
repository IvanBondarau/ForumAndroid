package by.bsuir.ivan_bondarau.forum.model

data class MessageWithAuthor(
    val message: Message,
    var author: User?
)