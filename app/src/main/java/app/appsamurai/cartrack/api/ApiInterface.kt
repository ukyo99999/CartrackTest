package app.appsamurai.cartrack.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class ApiInterface {
    private val entryPoint = "https://jsonplaceholder.typicode.com"

    fun <T> createApiCall(objectInterface: Class<T>, token: String?): T {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .method(original.method(), original.body())
                .header("Content-Type", "application/json")

            chain.proceed(builder.build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(entryPoint)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(objectInterface)
    }

}