package com.example.materialdesign.repository

import com.example.materialdesign.domain.ImageServerResponseData
import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageEveryDayAPI {

    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos?earth_date=2021-12-7")
    fun getVenus(@Query("api_key") apiKey: String): ArrayList<PhotosDataResponse>

    @GET("mars-photos/api/v1/rovers/curiosity/photos?earth_date=2021-12-7")
    fun getEarth(@Query("api_key") apiKey: String): ArrayList<PhotosDataResponse>

    @GET("mars-photos/api/v1/rovers/curiosity/photos?earth_date=2021-12-7")
    fun getMars(@Query("api_key") apiKey: String): Call<ArrayList<PhotosDataResponse>>

}