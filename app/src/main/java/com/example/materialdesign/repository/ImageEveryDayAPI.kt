package com.example.materialdesign.repository

import com.example.materialdesign.domain.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageEveryDayAPI {
    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<PODServerResponseData>

}