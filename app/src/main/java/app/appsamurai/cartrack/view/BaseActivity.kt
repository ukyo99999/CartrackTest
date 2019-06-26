package app.appsamurai.cartrack.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by Ukyo on 2019-06-26.
 *
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        if (null != intent.extras) {
            getBundleExtras(extras)
        }

        setContentView(layoutId())
        setTransparentStatusBar()
        initView()
        setToolbar()
        setListener()
        logicProcess()
    }

    /**
     * Get Layout ID
     *
     * @return layout xml id
     */
    protected abstract fun layoutId(): Int

    /**
     * Get intent bundle extras
     */
    protected abstract fun getBundleExtras(extras: Bundle)

    /**
     * Initial View
     */
    protected abstract fun initView()

    /**
     * Set Toolbar
     */
    protected abstract fun setToolbar()

    /**
     * Is this activity a transparent Status Bar
     *
     * @return
     */
    protected abstract fun transparentStatusBar(): Boolean

    /**
     * Set Listener
     */
    protected abstract fun setListener()

    /**
     * Process other logic
     */
    protected abstract fun logicProcess()

    /**
     * Set Transparent StatusBar
     */
    private fun setTransparentStatusBar() {

        if (transparentStatusBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = this.window

                val decorView = window.decorView
                val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                decorView.systemUiVisibility = option
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }
}