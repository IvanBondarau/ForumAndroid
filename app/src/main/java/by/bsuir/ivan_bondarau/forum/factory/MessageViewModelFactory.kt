package by.bsuir.ivan_bondarau.forum.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel

class MessageViewModelFactory constructor(
    private val messageRepository: MessageRepository,
    private val topicId: Int
) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessageViewModel(messageRepository, topicId) as T
    }
}