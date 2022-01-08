package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class PhotosDataResponse(
    @SerializedName("photos") val photos: ArrayList<ImageServerResponseData>
)