package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository

class MessageViewModel(messageRepository: MessageRepository, topicId: Int) : ViewModel() {

    val messages = messageRepository.findAllByTopicId(topicId)


}