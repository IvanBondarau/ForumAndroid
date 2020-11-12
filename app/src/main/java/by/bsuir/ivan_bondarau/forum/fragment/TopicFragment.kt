package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.activity.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : Fragment() {

    private lateinit var topicListFragment: TopicListFragment
    private lateinit var topicInputFragment: InputTopicFragment
    private lateinit var actionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicInputFragment = InputTopicFragment.newInstance()
        topicListFragment = TopicListFragment.newInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topic, container, false)
        actionButton = view.findViewById<FloatingActionButton>(R.id.floating_action_button)
        actionButton.setOnClickListener(::onAddButtonClicked)
        childFragmentManager.commit {
            replace(R.id.main_container, topicListFragment, "topicList")
        }
        childFragmentManager.executePendingTransactions()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): TopicFragment {
            return TopicFragment()
        }
    }

    fun setActionButtonVisible() {
        actionButton.isVisible = true
    }

    private fun onAddButtonClicked(view: View?) {
        actionButton.isVisible = false
        (activity as? MainActivity)?.useChildFragmentManager = true
        childFragmentManager.commit {
            add(R.id.bottom_container, topicInputFragment, "topicInput")
            addToBackStack(null)
        }
    }

}