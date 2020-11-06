package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.TopicViewModelFactory
import by.bsuir.ivan_bondarau.forum.viewmodel.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TopicFragment : Fragment() {

    @Inject
    lateinit var topicViewModelFactory: TopicViewModelFactory
    lateinit var topicViewModel: TopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicViewModel = ViewModelProvider(this, topicViewModelFactory)
            .get(TopicViewModel::class.java)
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
                adapter = TopicRecyclerViewAdapter(topicViewModel.topics,
                    activity?.supportFragmentManager!!
                )
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TopicFragment()

    }
}