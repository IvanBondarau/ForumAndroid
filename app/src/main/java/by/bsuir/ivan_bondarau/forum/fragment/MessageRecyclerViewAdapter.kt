package by.bsuir.ivan_bondarau.forum.fragment

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.holder.UserHolder
import by.bsuir.ivan_bondarau.forum.model.MessageWithAuthor
import by.bsuir.ivan_bondarau.forum.viewmodel.MessageViewModel
import com.like.LikeButton
import com.like.OnLikeListener
import java.text.SimpleDateFormat
import java.util.*

class MessageRecyclerViewAdapter(
    var messages: MutableList<MessageWithAuthor>,
    val messageViewModel: MessageViewModel,
    private val resources: Resources
) : RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_message_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = messages[position]

        if (item.author?.id == UserHolder.user?.id) {
            holder.cardView.setCardBackgroundColor(resources.getColor(R.color.cardBackgroundColored))
            holder.removeButton.setOnClickListener {
                messageViewModel.delete(item.message)
            }
            holder.removeButton.visibility = View.VISIBLE
        } else {
            holder.cardView.setCardBackgroundColor(resources.getColor(R.color.cardBackgroundLight))
            holder.removeButton.visibility = View.INVISIBLE
        }

        holder.creatorView.text =  item.author?.username ?: ""
        holder.textView.text = item.message.text
        holder.dateView.text = dateFormat.format(item.message.creationDate)
        holder.likesView.text = item.message.likes.toString() + " likes"
        holder.likeButton.isLiked = item.message.isLiked

        holder.likeButton.setOnLikeListener(
            object: OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    item.message.likes++
                    item.message.isLiked = true
                    messageViewModel.update(item.message)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    item.message.likes--
                    item.message.isLiked = false
                    messageViewModel.update(item.message)
                }

            }
        )

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val creatorView: TextView = view.findViewById(R.id.creator)
        val textView: TextView = view.findViewById(R.id.text)
        val dateView: TextView = view.findViewById(R.id.date)
        val likesView: TextView = view.findViewById(R.id.likes)
        val likeButton: LikeButton = view.findViewById(R.id.star_button)
        val cardView: CardView = view.findViewById(R.id.message_card)
        val removeButton: ImageButton = view.findViewById(R.id.remove_button)
    }

    companion object {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd 'в' HH:mm", Locale.US)
    }

}