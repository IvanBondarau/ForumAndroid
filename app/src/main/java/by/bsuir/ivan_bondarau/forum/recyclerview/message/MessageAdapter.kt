package by.bsuir.ivan_bondarau.forum.recyclerview.message

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.viewmodel.CardMessageViewModel

class MessageAdapter(private val messages: List<CardMessageViewModel>) :
    RecyclerView.Adapter<MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_cell, parent, false) as LinearLayout

        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val card: CardView = holder.view.findViewById(R.id.message_card)
        messages[position].bind(card)
    }

    override fun getItemCount() = messages.size
}