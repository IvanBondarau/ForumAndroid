package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.MessageViewModelFactory
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import by.bsuir.ivan_bondarau.forum.observer.Observer
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import by.bsuir.ivan_bondarau.forum.sync.MessageSyncTask
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessageListFragment : Fragment(), Observer {

    @Inject
    lateinit var messageRepository: MessageRepository

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var messages: MutableList<MessageWithAuthor>

    private lateinit var recyclerViewAdapter: MessageRecyclerViewAdapter

    private var topicId: Int? = null

    @Inject
    lateinit var messageSyncTask: MessageSyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageSyncTask.attach(this)
        if (savedInstanceState?.getInt("topicId") != null) {
            updateData(savedInstanceState)
        }

    }

    private fun updateData(savedInstanceState: Bundle) {
        topicId = savedInstanceState.getInt("topicId")
        messageViewModel =
            ViewModelProvider(this, MessageViewModelFactory(messageRepository, topicId!!))
                .get(MessageViewModel::class.java)

        messages = messageViewModel.messages.toMutableList()
        recyclerViewAdapter =
            MessageRecyclerViewAdapter(messages, messageViewModel, activity?.resources!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TEST2", savedInstanceState?.toString() ?: "BABABABAB")
        val bundle = arguments
        if (bundle?.getInt("topicId") != null) {
            updateData(bundle)
        }

        val view = inflater.inflate(R.layout.fragment_message_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = recyclerViewAdapter
            }
        }
        recyclerViewAdapter.notifyDataSetChanged()

        return view
    }

    fun updateMessages() {

        messageViewModel.reloadMessages()

        messages = messageViewModel.messages.toMutableList()
        Log.d("MessageList", messages.joinToString(", "))
        recyclerViewAdapter.messages.clear()
        recyclerViewAdapter.messages.addAll(messages)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("topicId", this.topicId!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(topicId: Int): MessageListFragment {
            val fragment = MessageListFragment()
            val bundle = Bundle(

            )
            bundle.putInt("topicId", topicId)

            fragment.arguments = bundle
            return fragment
        }


    }

    override fun update() {
        activity?.runOnUiThread {
            updateMessages()
        }
    }
}