package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopicViewModel(private val topicRepository: TopicRepository ): ViewModel() {
    var topics: List<Topic>

    init {

        topics = topicRepository.findAll()

    }

    fun reloadTopics() {
            topics = topicRepository.findAll()
    }
}