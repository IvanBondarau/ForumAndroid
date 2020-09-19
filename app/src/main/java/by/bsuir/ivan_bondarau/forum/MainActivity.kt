package by.bsuir.ivan_bondarau.forum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.model.Message
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.recyclerview.message.MessageAdapter
import by.bsuir.ivan_bondarau.forum.viewmodel.CardMessageViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val messages = listOf(
        CardMessageViewModel(
            Message(
                text = "Lorem ipsum dolor sit amet, no sit sonet noster dignissim, sed omnes omnesque ea, at movet admodum luptatum cum. Pri no malis petentium. Erat facer harum at ius, et duo amet feugiat. Et mutat simul mel, est eu amet senserit reprimique. Meis tempor euripidis et vel, cum viris nihil an.\n",
                author = User("Test user 1"),
                created = Calendar.getInstance().time
            )
        ),
        CardMessageViewModel(
            Message(
                text = "Lorem ipsum dolor sit amet, no sit sonet noster dignissim, sed omnes omnesque ea, at movet admodum luptatum cum. Pri no malis petentium. Erat facer harum at ius, et duo amet feugiat. Et mutat simul mel, est eu amet senserit reprimique. Meis tempor euripidis et vel, cum viris nihil an.\n",
                author = User("Test user 2"),
                created = Calendar.getInstance().time
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MessageAdapter(messages)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_messages).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}