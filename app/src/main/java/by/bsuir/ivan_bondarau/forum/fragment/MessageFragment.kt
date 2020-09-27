package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.MessageViewModelFactory
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel

/**
 * A fragment representing a list of Items.
 */
class MessageFragment: Fragment() {

    private val factory = MessageViewModelFactory.Instance

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var messages: MutableList<Message>

    private lateinit var recyclerViewAdapter: MessageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageViewModel = ViewModelProvider(this, factory).get(MessageViewModel::class.java)

        messages = messageViewModel.messages.toMutableList()
        recyclerViewAdapter =  MessageRecyclerViewAdapter(messages)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()

    }
}