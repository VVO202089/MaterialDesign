package com.example.materialdesign.repository

import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageEveryDayAPI {

    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMars(
        @Query("earth_date") earth_date: String,
        @Query("api_key") apiKey: String
    ): Call<PhotosDataResponse>

}