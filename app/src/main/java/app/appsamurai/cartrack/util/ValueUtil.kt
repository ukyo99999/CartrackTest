package app.appsamurai.cartrack.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * Created by Ukyo on 2019-07-01.
 *
 */
class ValueUtil {

    companion object {

        /**
         * Check if the permission got or not
         */
        fun isPermissionGet(context: Context, permission: String): Boolean {
            var getPermission = true

            if (Build.VERSION.SDK_INT >= 23 &&
                context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED
            ) {
                getPermission = false
            }

            return getPermission
        }
    }
}