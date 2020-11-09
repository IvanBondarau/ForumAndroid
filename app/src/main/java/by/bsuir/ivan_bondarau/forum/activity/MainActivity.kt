package by.bsuir.ivan_bondarau.forum.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.fragment.SettingsFragment
import by.bsuir.ivan_bondarau.forum.fragment.TopicFragment
import by.bsuir.ivan_bondarau.forum.holder.UserHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var useChildFragmentManager: Boolean = false
    private lateinit var topicFragment: TopicFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        topicFragment = TopicFragment.newInstance()

        supportFragmentManager.commit {
            add(R.id.root_container, topicFragment, "topicFragment")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val fragment: SettingsFragment = SettingsFragment.newInstance()
            supportFragmentManager.commit {
                replace(R.id.root_container, fragment, "settingsFragment")
                addToBackStack(null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if (useChildFragmentManager) {
            topicFragment.childFragmentManager.popBackStack()
            topicFragment.setActionButtonVisible()
            useChildFragmentManager = false
            return
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }


    fun disableKeyboard() {

        val inputManager: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        inputManager?.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            0
        )
    }

    fun logOut() {
        UserHolder.user = null
        val startLogin = Intent(this, LoginActivity::class.java)
        finish()
        startActivity(startLogin)
    }
}