package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private var topicId: Int? = null

    private lateinit var messageListFragment: MessageListFragment
    private lateinit var messageInputFragment: InputMessageFragment

    @Inject
    lateinit var topicRepository: TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle?.getInt("topicId") != null) {
            topicId = bundle.getInt("topicId")
        }

        messageListFragment = MessageListFragment.newInstance(topicId!!)
        messageInputFragment = InputMessageFragment.newInstance(topicId!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_message, container, false)

        childFragmentManager.commit {
            add(R.id.main_container, messageListFragment, "messageList")
            add(R.id.bottom_container, messageInputFragment, "messageInput")
        }
        childFragmentManager.executePendingTransactions()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(topicId: Int): MessageFragment {
            val fragment = MessageFragment()
            val bundle = Bundle()
            bundle.putInt("topicId", topicId)

            fragment.arguments = bundle
            return fragment
        }
    }
}