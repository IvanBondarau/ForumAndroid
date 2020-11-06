package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.MessageViewModelFactory
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import by.bsuir.ivan_bondarau.forum.repository.MessageRepository
import by.bsuir.ivan_bondarau.forum.repository.TopicRepository
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessageFragment: Fragment() {

    @Inject
    lateinit var messageRepository: MessageRepository

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var messages: MutableList<MessageWithAuthor>

    private lateinit var recyclerViewAdapter: MessageRecyclerViewAdapter

    private var topicId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST2",savedInstanceState?.toString() ?: "")
        if (savedInstanceState?.getInt("topicId") != null) {
            updateData(savedInstanceState)
        }

    }

    fun updateData(savedInstanceState: Bundle) {
        topicId = savedInstanceState.getInt("topicId")
        messageViewModel =
            ViewModelProvider(this, MessageViewModelFactory(messageRepository, topicId!!))
                .get(MessageViewModel::class.java)

        messages = messageViewModel.messages.toMutableList()
        recyclerViewAdapter =  MessageRecyclerViewAdapter(messages)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TEST2",savedInstanceState?.toString() ?: "BABABABAB")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("topicId", this.topicId!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(topicId: Int): MessageFragment {
            val fragment = MessageFragment()
            val bundle = Bundle(

            )
            bundle.putInt("topicId", topicId)

            fragment.arguments = bundle
            return fragment
        }


    }
}