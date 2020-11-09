package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository

class MessageViewModel(private val messageRepository: MessageRepository, private val topicId: Int) : ViewModel() {

    var messages = messageRepository.findAllByTopicId(topicId)

    fun reloadMessages() {
        messages = messageRepository.findAllByTopicId(topicId)
    }

}