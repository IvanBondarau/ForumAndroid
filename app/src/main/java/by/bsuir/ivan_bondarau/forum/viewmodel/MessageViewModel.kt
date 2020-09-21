package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import by.bsuir.ivan_bondarau.forum.model.Message

class MessageViewModel(private val messageRepository: MessageRepository) : ViewModel() {

    var message = MutableLiveData<Message>()

    fun setMessage(messageId: Int) {
        message.value = messageRepository.getById(messageId)
    }

}