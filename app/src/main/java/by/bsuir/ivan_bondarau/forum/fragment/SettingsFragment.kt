package by.bsuir.ivan_bondarau.forum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.activity.MainActivity
import by.bsuir.ivan_bondarau.forum.holder.UserHolder


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val email = view.findViewById<TextView>(R.id.email)
        email.text = UserHolder.user?.email
        val username = view.findViewById<TextView>(R.id.username)
        username.text = UserHolder.user?.username
        val logOutButton = view.findViewById<Button>(R.id.logOutButton)
        logOutButton.setOnClickListener(::onLogOut)
        return view
    }

    fun onLogOut(view: View?) {
        (activity as? MainActivity)?.logOut()
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}