package app.appsamurai.cartrack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val defaultZoom = 10f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

}
