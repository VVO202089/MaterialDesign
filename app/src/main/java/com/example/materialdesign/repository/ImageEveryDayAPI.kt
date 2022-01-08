package com.example.materialdesign.repository

import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageEveryDayAPI {

    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    /*
    @GET("venus-photos/api/v1/rovers/curiosity/photos?earth_date=2021-12-7")
    fun getVenus(@Query("api_key") apiKey: String): Call<PhotosDataResponse>

    @GET("EPIC/archive/natural/2019/05/30/png/epic_1b_20190530011359.png")
    fun getEarth(@Query("api_key") apiKey: String): Call<JsonPrimitive>
    "mars-photos/api/v1/rovers/curiosity/photos?earth_date=2021-12-7"
     */

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMars(@Query("earth_date") earth_date: String,@Query("api_key") apiKey: String): Call<PhotosDataResponse>

}