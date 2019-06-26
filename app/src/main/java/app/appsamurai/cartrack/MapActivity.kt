package app.appsamurai.cartrack

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import app.appsamurai.cartrack.api.callback.GetUerCallback
import app.appsamurai.cartrack.api.user.ApiGetUser
import app.appsamurai.cartrack.datamodel.UserGson
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class MapActivity : AppCompatActivity(), OnMapReadyCallback, GetUerCallback {
    private lateinit var mMap: GoogleMap
    private val defaultZoom = 6.5f
    private lateinit var users: List<UserGson>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        loadApi()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Move the camera to Taipei
        val taipei = LatLng(25.034480, 121.564514)
        mMap.addMarker(MarkerOptions().position(taipei).title("Taipei 101"))
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                taipei,
                defaultZoom
            )
        )
    }

    override fun onSuccess(users: List<UserGson>) {
        this.users = users
    }

    override fun onFail(errorMessage: String) {
        Log.e("onFail", "errorMessage=" + errorMessage)
        showSnackbar(errorMessage)
    }

    private fun loadApi() {
        val apiGetUser = ApiGetUser()
        apiGetUser.call(null, this)
    }

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}
