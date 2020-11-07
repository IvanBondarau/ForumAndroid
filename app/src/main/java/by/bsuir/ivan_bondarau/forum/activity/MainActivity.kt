package by.bsuir.ivan_bondarau.forum.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.fragment.InputTopicFragment
import by.bsuir.ivan_bondarau.forum.fragment.TopicFragment
import by.bsuir.ivan_bondarau.forum.fragment.TopicListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val fragment = TopicFragment.newInstance()

        supportFragmentManager.commit {
            add(R.id.root_container, fragment, "topicFragment")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            Log.d("ACt", "EMPTY")
            super.onBackPressed()
        }
    }
}