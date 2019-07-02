package app.appsamurai.cartrack.view.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import app.appsamurai.cartrack.R
import app.appsamurai.cartrack.util.PreferenceUtil
import app.appsamurai.cartrack.util.ViewUtil
import app.appsamurai.cartrack.util.sql.DbAccess
import app.appsamurai.cartrack.view.BaseActivity
import app.appsamurai.cartrack.view.map.MapActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun getBundleExtras(extras: Bundle) {

    }

    override fun initView() {
        setCountrySpinner()
    }

    override fun setToolbar() {

    }

    override fun setListener() {
        textLogin.setOnClickListener(this)
        textCreateAccount.setOnClickListener(this)
        textForgetPassword.setOnClickListener(this)
    }

    override fun transparentStatusBar(): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textLogin -> {
                doLogin()
            }
            R.id.textCreateAccount -> {
                ViewUtil.showSnackbar(v, getString(R.string.message_coming_soon))
            }
            R.id.textForgetPassword -> {
                ViewUtil.showSnackbar(v, getString(R.string.message_coming_soon))
            }
        }
    }

    override fun logicProcess() {
        addTestUser()
    }

    private fun setCountrySpinner() {
        val countryList =
            ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter = countryList
        spinnerCountry.setSelection(PreferenceUtil.itemPosition)

        spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                PreferenceUtil.itemPosition = position
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
            ViewUtil.showSnackbar(layout, getString(R.string.error_message_account_not_exist))
            return
        }

        if (!dbAccess.getPassword(inputUser.editText?.text.toString()).equals(inputPassword.editText?.text.toString())) {
            ViewUtil.showSnackbar(layout, getString(R.string.error_message_password_not_correct))
            return
        }

        loginSuccess()
    }

    private fun isValidEmail(input: String): Boolean {
        val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        return pattern.matcher(input).matches()
    }

    private fun loginSuccess() {
        ViewUtil.showToast(this@LoginActivity, getString(R.string.message_login_success))
        ViewUtil.gotoNextActivity(this@LoginActivity, MapActivity::class.java, null)
        finish()
    }

    /**
     * Add fake data for test
     */
    private fun addTestUser() {
        val db = DbAccess(this@LoginActivity)
        db.addUser("Chris", "abc123", "ukyo99999@gmail.com")
    }

}
