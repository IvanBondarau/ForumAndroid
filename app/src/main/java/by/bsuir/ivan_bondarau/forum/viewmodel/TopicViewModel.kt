package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository

class TopicViewModel(topicRepository: TopicRepository ): ViewModel() {
    val topics = topicRepository.findAll()
}