package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class PhotosDataResponse(
    @field:SerializedName("photos") val photos: ImageServerResponseData?
)