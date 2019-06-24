package app.appsamurai.cartrack

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import app.appsamurai.cartrack.sql.DbAccess
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setData()
        setListener()
        logicProcess()
    }

    private fun setData() {
        val countryList =
            ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter = countryList
    }

    private fun setListener() {
        textLogin.setOnClickListener(clickListener)
        textCreateAccount.setOnClickListener(clickListener)
        textForgetPassword.setOnClickListener(clickListener)
    }

    private fun logicProcess() {

    }

    private val clickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.textLogin -> {
                doLogin()
            }
            R.id.textCreateAccount -> {
                showSnackbar(getString(R.string.message_coming_soon))
            }
            R.id.textForgetPassword -> {
                showSnackbar(getString(R.string.message_coming_soon))
            }
        }
    }

    private fun doLogin() {
        inputUser.error = null
        inputPassword.error = null
        val dbAccess = DbAccess(this@LoginActivity)

        if (TextUtils.isEmpty(inputUser.editText?.text.toString())) {
            inputUser.error = getString(R.string.error_message_empty_email)
            return
        } else {
            inputUser.error = null
        }

        if (!isValidEmail(inputUser.editText?.text.toString())) {
            inputUser.error = getString(R.string.error_message_email_format)
            return
        }

        if (TextUtils.isEmpty(inputPassword.editText?.text.toString())) {
            inputPassword.error = getString(R.string.error_message_empty_password)
            return
        }

        if (!dbAccess.isEmailExist(inputUser.editText?.text.toString())) {
            showSnackbar(getString(R.string.error_message_account_not_exist))
            return
        }

        if (!dbAccess.getPassword(inputUser.editText?.text.toString()).equals(inputPassword.editText?.text.toString())) {
            showSnackbar(getString(R.string.error_message_password_not_correct))
            return
        }

        loginSuccess()
    }

    private fun isValidEmail(input: String): Boolean {
        val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        return pattern.matcher(input).matches()
    }

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun loginSuccess() {
        showSnackbar(getString(R.string.message_login_success))
    }

}
