package app.appsamurai.cartrack.api.user

import android.util.Log
import app.appsamurai.cartrack.api.ApiInterface
import app.appsamurai.cartrack.api.callback.GetUerCallback
import app.appsamurai.cartrack.datamodel.UserGson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class ApiGetUser {

    fun call(token: String?, callback: GetUerCallback) {
        val call = ApiInterface().createApiCall(IUser::class.java, token).getUserProfile()

        call.enqueue(object : Callback<List<UserGson>> {
            override fun onFailure(call: Call<List<UserGson>>, t: Throwable) {
                Log.e("ApiGetUser", "onFailure:" + t.message)
            }

            override fun onResponse(call: Call<List<UserGson>>, response: Response<List<UserGson>>) {
                if (response.isSuccessful) {
                    Log.d("ApiGetUser", "response successful")
                    response.body()?.let { callback.onSuccess(it) }

                } else {
                    try {
                        val apiResult = response.errorBody()!!.string()
                        Log.e("ApiGetUser", "response errorBody: " + apiResult)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}