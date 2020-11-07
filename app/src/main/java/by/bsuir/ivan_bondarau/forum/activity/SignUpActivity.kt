package by.bsuir.ivan_bondarau.forum.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.bsuir.ivan_bondarau.forum.R
import by.bsuir.ivan_bondarau.forum.factory.SignUpViewModelFactory
import by.bsuir.ivan_bondarau.forum.holder.UserHolder
import by.bsuir.ivan_bondarau.forum.model.User
import by.bsuir.ivan_bondarau.forum.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    @Inject
    lateinit var signUpViewModelFactory: SignUpViewModelFactory
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpViewModel =
            ViewModelProvider(this, signUpViewModelFactory).get(SignUpViewModel::class.java)

        setContentView(R.layout.activity_signup)

    }

    fun onClick(view: View) {
        val user = User(
            username = this.editTextUsername.text.toString(),
            email = this.editTextEmail.text.toString(),
            password = this.editTextPassword.text.toString(),
            passwordRepeat = this.editTextPasswordRepeat.text.toString()
        )

        val result = signUpViewModel.createAccount(user)

        this.errorText.text = result

        if (result == "OK") {
            UserHolder.user = user
            val startMain = Intent(this, MainActivity::class.java)
            finish()
            startActivity(startMain)
        }

    }
}