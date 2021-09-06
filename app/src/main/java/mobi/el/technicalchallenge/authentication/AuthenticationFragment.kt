package mobi.el.technicalchallenge.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.utils.isAgeValid
import mobi.el.technicalchallenge.utils.isEmailValid
import mobi.el.technicalchallenge.utils.isPasswordValid
import mobi.el.technicalchallenge.widgets.TCEditText

@AndroidEntryPoint
class AuthenticationFragment : Fragment() {

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    var isRegister: Boolean = false
    lateinit var emailEditText: TCEditText
    lateinit var passwordEditText: TCEditText
    lateinit var ageEditText: TCEditText
    lateinit var loginButton: Button
    lateinit var titleTextView: TextView
    lateinit var bottomLeftTextView: TextView
    lateinit var bottomRightTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_authentication, container, false)

        emailEditText = view.findViewById<View>(R.id.edittext_email) as TCEditText
        passwordEditText = view.findViewById<View>(R.id.edittext_password) as TCEditText
        ageEditText = view.findViewById<View>(R.id.edittext_age) as TCEditText
        loginButton = view.findViewById<View>(R.id.button_login) as Button
        titleTextView = view.findViewById<View>(R.id.textview_login_title) as TextView
        bottomLeftTextView = view.findViewById<View>(R.id.textview_dont_have_an_account) as TextView
        bottomRightTextView = view.findViewById<View>(R.id.textview_register) as TextView

        emailEditText.setInputEditText(TCEditText.EditTextType.EMAIL)
        passwordEditText.setInputEditText(TCEditText.EditTextType.PASSWORD)
        ageEditText.setInputEditText(TCEditText.EditTextType.AGE)

        bottomRightTextView.setOnClickListener(View.OnClickListener {
            redraw()
        })

        loginButton.setOnClickListener(View.OnClickListener {
            authenticate()
        })

        authenticationViewModel.observerRegistrationValidity().observe(viewLifecycleOwner, {
            if (it) {
                onAuthenticationSuccesful()
            } else {
                Toast.makeText(context, R.string.register_error, Toast.LENGTH_LONG).show()
            }
        })

        authenticationViewModel.observerLoginValidity().observe(viewLifecycleOwner, {
            if (it) {
                onAuthenticationSuccesful()
            } else {
                Toast.makeText(context, R.string.login_error, Toast.LENGTH_LONG).show()
            }
        })
        return view
    }


    private fun redraw() {
        isRegister = !isRegister
        if (isRegister) {
            ageEditText.visibility = View.VISIBLE
            loginButton.text = resources.getString(R.string.register)
            titleTextView.text = resources.getString(R.string.register)
            bottomLeftTextView.text = resources.getString(R.string.already_have_an_account)
            bottomRightTextView.text = resources.getString(R.string.login)
        } else {
            ageEditText.visibility = View.GONE
            loginButton.text = resources.getString(R.string.login)
            titleTextView.text = resources.getString(R.string.login)
            bottomLeftTextView.text = resources.getString(R.string.dont_have_an_account)
            bottomRightTextView.text = resources.getString(R.string.register)
        }
    }

    private fun authenticate() {
        var wrongInput = false;
        var email = emailEditText.getInput()
        var password = passwordEditText.getInput()

        if (!email.isEmailValid()) {
            emailEditText.setWrongInputDrawable()
            emailEditText.setErrorText(getString(R.string.invalid_email))
            wrongInput = true
        } else {
            emailEditText.removeErrorText()
            emailEditText.removeWrongInputDrawable()
        }

        if (!password.isPasswordValid()) {
            wrongInput = true
            passwordEditText.setWrongInputDrawable()
            passwordEditText.setErrorText(getString(R.string.invalid_password))
        } else {
            passwordEditText.removeErrorText()
            passwordEditText.removeWrongInputDrawable()
        }
        if (isRegister) {
            if (!ageEditText.getInput().isAgeValid()) {
                wrongInput = true
                ageEditText.setWrongInputDrawable()
                ageEditText.setErrorText(getString(R.string.invalid_age))
            } else {
                ageEditText.removeErrorText()
                ageEditText.removeWrongInputDrawable()
            }
            if (!wrongInput)
                authenticationViewModel.register(
                    email,
                    password, ageEditText.getInput().toInt()
                )
        } else {
            if (!wrongInput)
                authenticationViewModel.login(email, password)
        }
    }

    private fun onAuthenticationSuccesful() {
        findNavController().navigate(R.id.action_authenticationFragment_to_galleryFragment)
    }
}