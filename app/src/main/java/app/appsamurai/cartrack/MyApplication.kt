package app.appsamurai.cartrack

import android.app.Application
import android.os.Build
import app.appsamurai.cartrack.util.PreferenceUtil
import com.facebook.stetho.Stetho

/**
 * Created by Ukyo on 2019-06-21.
 *
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        PreferenceUtil.init(this)

        if (!isRoboUnitTest())
            Stetho.initializeWithDefaults(this)
    }

    private fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

}