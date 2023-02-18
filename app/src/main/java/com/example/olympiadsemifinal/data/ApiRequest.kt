package com.example.olympiadsemifinal.data

import com.example.olympiadsemifinal.data.response.ServiceListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("semi-final-data.json")
    suspend fun getServiceList(): ServiceListResponse
}