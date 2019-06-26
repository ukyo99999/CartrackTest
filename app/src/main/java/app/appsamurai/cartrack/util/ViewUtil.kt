package app.appsamurai.cartrack.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

/**
 * Created by Ukyo on 2019-06-27.
 *
 */
class ViewUtil {

    companion object {
        /**
         * Show Toast message
         *
         * @param context context
         * @param showText
         */
        fun showToast(context: Context?, showText: String) {
            Toast.makeText(context, showText, Toast.LENGTH_LONG).show()
        }

        /**
         * Show Snackbar
         * @param view
         * @param message
         */
        fun showSnackbar(view: View, message: String) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        /**
         * Go to next activity
         *
         * @param context
         * @param className
         * @param bundle
         */
        fun gotoNextActivity(context: Context, className: Class<*>, bundle: Bundle?) {
            val intent = Intent()
            intent.setClass(context, className)

            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }
    }

}