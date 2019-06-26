package app.appsamurai.cartrack.view.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.appsamurai.cartrack.R
import app.appsamurai.cartrack.datamodel.UserGson
import kotlinx.android.synthetic.main.fragment_card.view.*

private const val PARAM_USER = "user"

class CardFragment : Fragment() {
    private var user: UserGson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(PARAM_USER) as UserGson?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_card, container, false)

        rootView.textName.text = user?.name
        rootView.textEmail.text = user?.email
        rootView.textSuite.text = user?.address?.suite + ","
        rootView.textPhone.text = user?.phone
        rootView.textWeb.text = user?.website
        rootView.textCompanyName.text = user?.company?.name
        rootView.textStreet.text = user?.address?.street
        rootView.textCity.text = user?.address?.city + ","
        rootView.textZipcode.text = user?.address?.zipcode
        rootView.textCompanyCatchPhrase.text = user?.company?.catchPhrase
        rootView.textCompanyBs.text = user?.company?.bs

        return rootView
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
