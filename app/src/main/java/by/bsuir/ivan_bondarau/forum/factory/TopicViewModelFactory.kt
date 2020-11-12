package by.bsuir.ivan_bondarau.forum.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository
import by.bsuir.ivan_bondarau.forum.viewmodel.TopicViewModel
import javax.inject.Inject

class TopicViewModelFactory @Inject constructor(private val topicRepository: TopicRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopicViewModel(topicRepository) as T
    }
}