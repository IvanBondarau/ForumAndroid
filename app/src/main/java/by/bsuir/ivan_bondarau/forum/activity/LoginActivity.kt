package by.bsuir.ivan_bondarau.forum.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.database.AppDatabase
import by.bsuir.ivan_bondarau.forum.factory.LoginViewModelFactory
import by.bsuir.ivan_bondarau.forum.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: LoginViewModelFactory
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        setContentView(R.layout.activity_login)
    }

    fun onLogIn(view: View) {
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

        if (loginViewModel.logIn(username, password)) {
            val startMain = Intent(this, MainActivity::class.java)
            finish()
            startActivity(startMain)
        }
    }

    fun onSignUp(view: View) {
        val startMain = Intent(this, SignUpActivity::class.java)
        finish()
        startActivity(startMain)
    }
}