package app.appsamurai.cartrack.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ukyo on 2019-06-26.
 *
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var activity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initView(savedInstanceState, rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListener()
        logicProcess()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    /**
     *  Get Layout ID
     *
     * @return layout xml id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * Initial View
     */
    protected abstract fun initView(savedInstanceState: Bundle?, view: View)

    /**
     * Set Listener
     */
    protected abstract fun setListener()

    /**
     * Other Logic Process
     */
    protected abstract fun logicProcess()

}