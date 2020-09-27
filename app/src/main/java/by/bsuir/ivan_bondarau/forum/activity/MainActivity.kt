package by.bsuir.ivan_bondarau.forum.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.fragment.MessageFragment


class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MessageFragment.newInstance()

        supportFragmentManager.commit {
            add(R.id.root_container, fragment, "root")
        }
    }
}