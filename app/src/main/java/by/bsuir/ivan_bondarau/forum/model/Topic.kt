package by.bsuir.ivan_bondarau.forum.model

data class Topic(
    var name: String,
    var messages: List<Message> = mutableListOf()
)