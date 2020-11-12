package by.bsuir.ivan_bondarau.forum.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.activity.MainActivity
import by.bsuir.ivan_bondarau.forum.holder.UserHolder
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class InputMessageFragment : Fragment() {

    @Inject
    lateinit var messageRepository: MessageRepository

    private lateinit var textInput: EditText

    private var topicId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle?.getInt("topicId") != null) {
            topicId = bundle.getInt("topicId")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.input_message, container, false)
        val messageButton = view.findViewById<Button>(R.id.sendMessageButton)
        textInput = view.findViewById<EditText>(R.id.messageTextInput)
        messageButton.setOnClickListener(::onMessageSend)
        return view
    }

    private fun onMessageSend(view: View?) {

        val text = textInput.text.toString()
        val user = UserHolder.user
        val date = Calendar.getInstance().time
        val message = Message(null, text, date, topicId!!, user!!.id!!)
        messageRepository.save(message)

        (parentFragment?.childFragmentManager
            ?.findFragmentById(R.id.main_container) as MessageListFragment).updateMessages()

        parentFragment?.childFragmentManager?.popBackStack()

        textInput.text.clear()


    }

    companion object {
        @JvmStatic
        fun newInstance(topicId: Int): InputMessageFragment {
            val fragment = InputMessageFragment()
            val bundle = Bundle()
            bundle.putInt("topicId", topicId)

            fragment.arguments = bundle
            return fragment
        }
    }
}