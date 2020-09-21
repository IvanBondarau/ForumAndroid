package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.MessageViewModelFactory
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel

/**
 * A fragment representing a list of Items.
 */
class MessageFragment: Fragment() {

    private val factory = MessageViewModelFactory.Instance

    private lateinit var messageIdList: List<Int>
    private lateinit var messageList: List<MessageViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            messageIdList = it.getIntArray("MESSAGE_ID_LIST")?.toList() ?: listOf()
            messageList = messageIdList.map { id ->
                val item = ViewModelProvider(this, factory).get(MessageViewModel::class.java)
                item.setMessage(id)
                item
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MessageRecyclerViewAdapter(messageList)
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(messageIdList: List<Int>) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putIntArray("MESSAGE_ID_LIST", messageIdList.toIntArray())
                }
            }

    }
}