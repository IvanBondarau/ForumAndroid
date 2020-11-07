package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.TopicViewModelFactory
import by.bsuir.ivan_bondarau.forum.model.Topic
import by.bsuir.ivan_bondarau.forum.viewmodel.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TopicListFragment : Fragment() {

    @Inject
    lateinit var topicViewModelFactory: TopicViewModelFactory
    lateinit var topicViewModel: TopicViewModel
    lateinit var topics: MutableList<Topic>
    lateinit var recyclerViewAdapter: TopicRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicViewModel = ViewModelProvider(this, topicViewModelFactory)
            .get(TopicViewModel::class.java)
        topics = topicViewModel.topics.toMutableList()
        recyclerViewAdapter = TopicRecyclerViewAdapter(topics,
            activity?.supportFragmentManager!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topic_item_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = recyclerViewAdapter
            }
        }

        return view
    }

    fun updateTopics() {

        topicViewModel.reloadTopics()

        topics = topicViewModel.topics.toMutableList()
        recyclerViewAdapter.topics.clear()
        recyclerViewAdapter.topics.addAll(topics)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TopicListFragment()

    }
}