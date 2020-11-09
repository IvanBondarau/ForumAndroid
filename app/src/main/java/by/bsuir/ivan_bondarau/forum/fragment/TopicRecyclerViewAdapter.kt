package by.bsuir.ivan_bondarau.forum.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.model.Topic

import java.text.SimpleDateFormat
import java.util.*

class TopicRecyclerViewAdapter(
    val topics: MutableList<Topic>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<TopicRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_topic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = topics[position]
        holder.nameView.text = topic.name
        holder.lastMessageDateView.text =
             if (topic.lastMessageDate == null) ""
                else dateFormat.format(topic.lastMessageDate!!)

        holder.messagesCountView.text =
            if (topic.messagesCount == null) ""
            else topic.messagesCount.toString() + " messages"

        holder.card.setOnClickListener {
            val item = topics[position]

            val messageFragment = MessageFragment.newInstance(item.topicId!!)

            fragmentManager.commit {
                replace(R.id.root_container, messageFragment, "messageFragment")
                addToBackStack(null)
            }
            fragmentManager.executePendingTransactions()

        }
    }

    override fun getItemCount(): Int = topics.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val lastMessageDateView: TextView = view.findViewById(R.id.last_message_date)
        val messagesCountView: TextView = view.findViewById(R.id.messages_count)
        val card: CardView = view.findViewById(R.id.topic_card)
    }


    companion object {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd 'в' HH:mm", Locale.US)
    }
}