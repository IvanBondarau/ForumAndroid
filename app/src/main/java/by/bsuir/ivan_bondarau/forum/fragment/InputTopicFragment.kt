package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.holder.UserHolder
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class InputTopicFragment : Fragment() {

    @Inject
    lateinit var topicRepository: TopicRepository


    @Inject
    lateinit var messageRepository: MessageRepository

    private lateinit var textInput: EditText
    private lateinit var titleInput: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.input_topic, container, false)
        val createButton = view.findViewById<Button>(R.id.createButton)
        textInput = view.findViewById<EditText>(R.id.textInput)
        titleInput = view.findViewById<EditText>(R.id.titleInput)
        createButton.setOnClickListener(::onTopicCreated)
        return view
    }

    private fun onTopicCreated(view: View?) {

        val title = titleInput.text.toString()
        val text = textInput.text.toString()
        val user = UserHolder.user
        val date = Calendar.getInstance().time
        val topic = Topic(
            null,
            title,
            date
        )

        topicRepository.create(topic)

        val message = Message(null, text, user!!.id!!, date, topic.id!!)
        messageRepository.save(message)

        (parentFragment?.childFragmentManager
            ?.findFragmentById(R.id.main_container) as TopicListFragment).updateTopics()

        titleInput.text.clear()
        textInput.text.clear()

    }

    companion object {
        @JvmStatic
        fun newInstance(): InputTopicFragment {
            return InputTopicFragment()
        }
    }
}