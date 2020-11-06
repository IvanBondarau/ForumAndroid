package by.bsuir.ivan_bondarau.forum.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.fragment.TopicFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val fragment = TopicFragment.newInstance()

        supportFragmentManager.commit {
            add(R.id.root_container, fragment, "root")
        }
    }
}