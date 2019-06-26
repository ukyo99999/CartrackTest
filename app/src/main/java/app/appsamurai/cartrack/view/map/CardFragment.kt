package app.appsamurai.cartrack.view.map

import android.os.Bundle
import android.view.View
import app.appsamurai.cartrack.R
import app.appsamurai.cartrack.datamodel.UserGson
import app.appsamurai.cartrack.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_card.view.*

private const val PARAM_USER = "user"

class CardFragment : BaseFragment() {
    private var user: UserGson? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_card
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        view.textName.text = user?.name
        view.textEmail.text = user?.email
        view.textSuite.text = user?.address?.suite + ","
        view.textPhone.text = user?.phone
        view.textWeb.text = user?.website
        view.textCompanyName.text = user?.company?.name
        view.textStreet.text = user?.address?.street
        view.textCity.text = user?.address?.city + ","
        view.textZipcode.text = user?.address?.zipcode
        view.textCompanyCatchPhrase.text = user?.company?.catchPhrase
        view.textCompanyBs.text = user?.company?.bs
    }

    override fun setListener() {

    }

    override fun logicProcess() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(PARAM_USER) as UserGson?
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(userGson: UserGson) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_USER, userGson)
                }
            }
    }

}
