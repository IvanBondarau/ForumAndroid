package by.bsuir.ivan_bondarau.forum.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel
import java.text.SimpleDateFormat
import java.util.*

class MessageRecyclerViewAdapter(
    private val messages: List<MessageWithAuthor>
) : RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = messages[position]
        holder.creatorView.text =  item.author?.username ?: ""
        holder.textView.text = item.message.text
        holder.dateView.text = if (item.message.created != null)
            dateFormat.format(item.message.created)
            else ""

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val creatorView: TextView = view.findViewById(R.id.creator)
        val textView: TextView = view.findViewById(R.id.text)
        val dateView: TextView = view.findViewById(R.id.date)
    }

    companion object {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd 'в' HH:mm", Locale.US)
    }

}