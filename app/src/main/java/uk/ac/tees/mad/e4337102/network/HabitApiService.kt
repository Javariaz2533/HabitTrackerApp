package uk.ac.tees.mad.e4337102.network

import retrofit2.http.GET

interface HabitApiService {

    // Example endpoint: GET /habits -> [{id,name,description}, ...]
    @GET("habits")
    suspend fun getHabits(): List<HabitDto>
}
