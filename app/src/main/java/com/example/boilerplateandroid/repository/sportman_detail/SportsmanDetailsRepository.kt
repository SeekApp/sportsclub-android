package com.example.boilerplateandroid.repository.sportman_detail

import com.example.boilerplateandroid.model.Sportsman
import com.example.boilerplateandroid.utils.NetworkResult

interface SportsmanDetailsRepository {
    suspend fun fetchSportsmanDetails(id: String) : NetworkResult<Sportsman>
}