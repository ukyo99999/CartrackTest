package app.appsamurai.cartrack.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Ukyo on 2019-07-02.
 *
 */
object PreferenceUtil {
    private const val PREFERENCE_NAME = "cartrack"
    private const val COUNTRY_ITEM_POSITION = "country_item_position"
    private lateinit var preference: SharedPreferences

    fun init(context: Context) {
        preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    var itemPosition: Int
        get() {
            return preference.getInt(COUNTRY_ITEM_POSITION, 0)
        }
        set(value) {
            preference.edit().putInt(COUNTRY_ITEM_POSITION, value).apply()
        }

}