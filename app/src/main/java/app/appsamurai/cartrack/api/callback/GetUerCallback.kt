package app.appsamurai.cartrack.api.callback

import app.appsamurai.cartrack.datamodel.UserGson

/**
 * Created by Ukyo on 2019-06-26.
 *
 */
interface GetUerCallback {
    fun onSuccess(users: List<UserGson>)
    fun onFail(errorMessage: String)
}