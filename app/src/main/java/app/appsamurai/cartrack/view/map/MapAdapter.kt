package app.appsamurai.cartrack.view.map

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import app.appsamurai.cartrack.datamodel.UserGson

/**
 * Created by Ukyo on 2019-06-26.
 *
 */
class MapAdapter(fragmentManager: FragmentManager, users: List<UserGson>) : FragmentPagerAdapter(fragmentManager) {
    private val userList = users

    override fun getItem(position: Int): Fragment {
        return CardFragment.newInstance(userList[position])
    }

    override fun getCount(): Int {
        return userList.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.8f
    }


}