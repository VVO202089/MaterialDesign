package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class ImageServerResponseData(
    @SerializedName("id") val id: Int?,
    @SerializedName("sol") val sol: Int?,
    @SerializedName("camera") val camera: CameraData?,
    @SerializedName("img_src") val imgSrc: String?,
    @SerializedName("earth_date") val earthDate: String?,
    @SerializedName("rover") val rover: RoverData?
)