package by.bsuir.ivan_bondarau.forum.viewmodel

import android.widget.TextView
import androidx.cardview.widget.CardView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.model.Message
import java.text.SimpleDateFormat
import java.util.*

class CardMessageViewModel(private val message: Message) {

    companion object {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd 'в' HH:mm", Locale.US);
    }

    fun bind(view: CardView) {
        val content = view.findViewById<TextView>(R.id.text_content)
        content.text = message.text
        val dateView = view.findViewById<TextView>(R.id.date)
        if (message.created != null) {
            dateView.text = dateFormat.format(message.created)
        }
        val creatorView = view.findViewById<TextView>(R.id.creator)
        creatorView.text = message.author?.login
    }
}