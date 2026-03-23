package uk.ac.tees.mad.e4337102.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {


    private const val BASE_URL = "https://mockapi.io/clone/695c166c79f2f34749d36cae/"

    fun api(): HabitApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(HabitApiService::class.java)
    }
}
