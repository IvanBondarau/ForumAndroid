package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : Fragment() {

    private lateinit var topicListFragment: TopicListFragment
    private lateinit var topicInputFragment: InputTopicFragment

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

        childFragmentManager.commit {
            replace(R.id.main_container, topicListFragment, "topicList")
            replace(R.id.bottom_container, topicInputFragment, "topicInput")
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
}