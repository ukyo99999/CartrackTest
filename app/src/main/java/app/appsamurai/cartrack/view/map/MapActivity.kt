package app.appsamurai.cartrack.view.map

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.View
import app.appsamurai.cartrack.R
import app.appsamurai.cartrack.api.callback.GetUerCallback
import app.appsamurai.cartrack.api.user.ApiGetUser
import app.appsamurai.cartrack.datamodel.UserGson
import app.appsamurai.cartrack.util.ViewUtil
import app.appsamurai.cartrack.view.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class MapActivity : BaseActivity(), OnMapReadyCallback, GetUerCallback {
    private lateinit var mMap: GoogleMap
    private val defaultZoom = 3f
    private lateinit var users: List<UserGson>

    override fun layoutId(): Int {
        return R.layout.activity_map
    }

    override fun getBundleExtras(extras: Bundle) {

    }

    override fun initView() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun setToolbar() {

    }

    override fun transparentStatusBar(): Boolean {
        return false
    }

    override fun setListener() {

    }

    override fun logicProcess() {
        loadApi()
    }

    override fun onClick(v: View?) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onSuccess(users: List<UserGson>) {
        this.users = users
        setViewpager()
        moveLocation(0)
    }

    override fun onFail(errorMessage: String) {
        if (!TextUtils.isEmpty(errorMessage)) {
            ViewUtil.showSnackbar(layout, errorMessage)
        }
    }

    private fun loadApi() {
        val apiGetUser = ApiGetUser()
        apiGetUser.call(null, this)
    }

    private fun setViewpager() {
        val adapter = MapAdapter(supportFragmentManager, this.users)
        pager.adapter = adapter
        pager.pageMargin = 100
        pager.addOnPageChangeListener(pageChangeListener)
    }

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            moveLocation(position)
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    private fun moveLocation(position: Int) {
        val location =
            LatLng(users[position].address?.geo?.lat!!.toDouble(), users[position].address?.geo?.lng!!.toDouble())
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location,
                defaultZoom
            )
        )
        mMap.addMarker(MarkerOptions().position(location).title(users[position].username))
    }

}
