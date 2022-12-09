package com.example.boilerplateandroid.data

import com.example.boilerplateandroid.data.response.SportsmanNearbyResponse
import com.example.boilerplateandroid.data.response.SportsmanResponse
import com.example.boilerplateandroid.model.Sportsman
import retrofit2.http.GET
import retrofit2.http.Path

interface SportClubAPIService {
    @GET("sportsmannearby")
    suspend fun fetchSportsmanNearby() : SportsmanNearbyResponse

    @GET("sportsman/{userId}")
    suspend fun fetchSportsmanById(@Path("userId") userId : String) : SportsmanResponse
}