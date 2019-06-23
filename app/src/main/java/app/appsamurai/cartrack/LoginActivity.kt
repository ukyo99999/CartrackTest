package app.appsamurai.cartrack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val countryList =
            ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter = countryList
    }
}
