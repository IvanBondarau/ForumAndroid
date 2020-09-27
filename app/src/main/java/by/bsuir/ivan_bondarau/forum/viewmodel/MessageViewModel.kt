package by.bsuir.ivan_bondarau.forum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository

class MessageViewModel(private val messageRepository: MessageRepository) : ViewModel() {

    val messages = messageRepository.findAll()


}