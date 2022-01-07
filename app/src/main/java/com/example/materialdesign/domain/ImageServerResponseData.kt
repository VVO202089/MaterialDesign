package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class ImageServerResponseData(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("sol") val sol: Int?,
    @field:SerializedName("camera") val camera: CameraData?,
    @field:SerializedName("img_src") val img_src: String?,
    @field:SerializedName("earth_date") val earth_date: String?,
    @field:SerializedName("rover") val rover: RoverData?
)