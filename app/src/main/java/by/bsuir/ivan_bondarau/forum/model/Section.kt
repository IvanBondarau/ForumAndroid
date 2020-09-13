package by.bsuir.ivan_bondarau.forum.model

data class Section(
    var name: String,
    var topics: List<Topic> = mutableListOf()
)