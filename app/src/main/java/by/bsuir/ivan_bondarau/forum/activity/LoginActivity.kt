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

class LoginActivity : AppCompatActivity() {

    lateinit var database: AppDatabase

    private lateinit var factory: LoginViewModelFactory
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDatabase.applicationContext = applicationContext
        database = AppDatabase.Instance

        LoginViewModelFactory.userDao = database.userDao()
        factory = LoginViewModelFactory.Instance

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
}