package app.appsamurai.cartrack.api.user

import app.appsamurai.cartrack.datamodel.UserGson
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
interface IUser {
    @GET("/users")
    fun getUserProfile(): Call<List<UserGson>>
}